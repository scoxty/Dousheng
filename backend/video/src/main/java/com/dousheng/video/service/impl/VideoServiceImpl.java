package com.dousheng.video.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dousheng.dto.common.VideoInfoDTO;
import com.dousheng.dto.req.comment.GetVideoCommentCountsReqDTO;
import com.dousheng.dto.req.favorite.GetTotalLikeMeCountReqDTO;
import com.dousheng.dto.req.favorite.GetVideoLikeCountsReqDTO;
import com.dousheng.dto.req.video.GetPublishListReqDTO;
import com.dousheng.dto.req.video.GetWorkCountReqDTO;
import com.dousheng.dto.req.video.PublishReqDTO;
import com.dousheng.dto.resp.comment.GetVideoCommentCountsRespDTO;
import com.dousheng.dto.resp.favorite.GetTotalLikeMeCountRespDTO;
import com.dousheng.dto.resp.favorite.GetVideoLikeCountsRespDTO;
import com.dousheng.dto.resp.video.GetPublishListRespDTO;
import com.dousheng.dto.resp.video.GetWorkCountRespDTO;
import com.dousheng.dto.resp.video.PublishRespDTO;
import com.dousheng.service.CommentRpcService;
import com.dousheng.service.FavoriteRpcService;
import com.dousheng.service.RelationRpcService;
import com.dousheng.video.common.convention.exception.ClientException;
import com.dousheng.video.common.convention.exception.RemoteException;
import com.dousheng.video.dao.entity.VideoDO;
import com.dousheng.video.dao.mapper.VideoMapper;
import com.dousheng.video.service.VideoService;
import com.github.benmanes.caffeine.cache.Cache;
import io.netty.util.internal.ThreadLocalRandom;
import lombok.RequiredArgsConstructor;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

import static com.dousheng.video.common.constant.MqConstant.HOT_VIDEO_TOPIC;
import static com.dousheng.video.common.constant.PageConstant.COMMON_PAGE_SIZE;
import static com.dousheng.video.common.constant.PageConstant.COMMON_START_PAGE;
import static com.dousheng.video.common.constant.RedisCacheConstant.*;
import static com.dousheng.video.common.constant.SuccessBaseRespConstant.SUCCESS_CODE;
import static com.dousheng.video.common.constant.SuccessBaseRespConstant.SUCCESS_MESSAGE;
import static com.dousheng.video.common.enums.VideoErrorCodeEnum.*;
import static com.dousheng.video.common.enums.YesOrNoEnum.NO;

@Service
@RequiredArgsConstructor
public class VideoServiceImpl extends ServiceImpl<VideoMapper, VideoDO> implements VideoService {
    private static final Log log = LogFactory.get();

    @DubboReference(timeout = 4000, retries = 3, loadbalance = "roundrobin")
    private FavoriteRpcService favoriteRpcService;
    @DubboReference(timeout = 4000, retries = 3, loadbalance = "roundrobin")
    private CommentRpcService commentRpcService;

    private final RedissonClient redissonClient;
    private final RedisTemplate redisTemplate;
    private final RocketMQTemplate rocketMQTemplate;
    private final Cache<String, List<VideoDO>> publishListLocalCache;
    private final Cache<Long, Integer> workCountLocalCache;
    private final RBloomFilter<Long> userIdCachePenetrationBloomFilter;

    private final ExecutorService executorService = new ThreadPoolExecutor(
            Runtime.getRuntime().availableProcessors() << 1,
            Runtime.getRuntime().availableProcessors() << 2,
            60L,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(200),
            new ThreadPoolExecutor.CallerRunsPolicy());

    @Override
    public PublishRespDTO publish(PublishReqDTO requestParam) {
        this.checkParam(requestParam);

        Date now = new Date();
        VideoDO videoDO = VideoDO.builder()
                .id(IdWorker.getId())
                .authorId(requestParam.getUserId())
                .playUrl(requestParam.getVideoInfo().getPlayUrl())
                .coverUrl(requestParam.getVideoInfo().getCoverUrl())
                .title(requestParam.getVideoInfo().getTitle())
                .width(requestParam.getVideoInfo().getWidth())
                .height(requestParam.getVideoInfo().getHeight())
                .isPrivate(NO.type)
                .createTime(now)
                .updateTime(now)
                .build();
        baseMapper.insert(videoDO);

        redisTemplate.opsForZSet().add(
                String.format(GET_PUBLISH_LIST_KEY, requestParam.getUserId(), videoDO.getIsPrivate()),
                JSON.toJSONString(videoDO),
                videoDO.getCreateTime().getTime()
        );
        redisTemplate.expire(
                String.format(GET_PUBLISH_LIST_KEY, requestParam.getUserId(), videoDO.getIsPrivate()),
                GET_PUBLISH_LIST_BASE_TTL + ThreadLocalRandom.current().nextInt(31),
                TimeUnit.SECONDS
        );
        addVideoToLocalCache(requestParam.getUserId(), videoDO);

        userIdCachePenetrationBloomFilter.add(requestParam.getUserId());

        redisTemplate.delete(String.format(GET_WORKCOUNT_BY_AUTHORID_KEY, requestParam.getUserId()));
        workCountLocalCache.invalidate(requestParam.getUserId());

        rocketMQTemplate.syncSend(HOT_VIDEO_TOPIC, BeanUtil.copyProperties(videoDO, VideoInfoDTO.class));

        PublishRespDTO respDTO = PublishRespDTO.builder()
                .code(SUCCESS_CODE)
                .message(SUCCESS_MESSAGE)
                .build();

        return respDTO;
    }

    @Override
    public GetPublishListRespDTO getPublishList(GetPublishListReqDTO requestParam) {
        this.checkParam(requestParam);

        GetPublishListRespDTO respDTO = GetPublishListRespDTO.builder().code(SUCCESS_CODE).message(SUCCESS_MESSAGE).build();

        // 作者不存在直接返回空
        // TODO: 如果作者不存在，是否需要返回错误信息？
        if (!userIdCachePenetrationBloomFilter.contains(requestParam.getUserId())) {
           return respDTO;
        }

        List<VideoDO> videoDOList = this.getBasePublishList(requestParam);

        List<VideoInfoDTO> videoInfoDTOList = new ArrayList<>();
        for (int i = 0; i < videoDOList.size(); i++) {
            VideoDO videoDO = videoDOList.get(i);
            VideoInfoDTO videoInfoDTO = BeanUtil.copyProperties(videoDO, VideoInfoDTO.class);
            Integer likeCounts = 0, commentCounts = 0;

            Future<Integer> likeCountsFuture = executorService.submit(() -> {
                GetVideoLikeCountsReqDTO getVideoLikeCountsReqDTO = GetVideoLikeCountsReqDTO.builder().videoId(videoDO.getId()).build();
                GetVideoLikeCountsRespDTO getVideoLikeCountsRespDTO = favoriteRpcService.getVideoLikeCounts(getVideoLikeCountsReqDTO);
                return getVideoLikeCountsRespDTO.getLikeCounts();
            });
            Future<Integer> commentCountsFuture = executorService.submit(() -> {
                GetVideoCommentCountsReqDTO getVideoCommentCountsReqDTO = GetVideoCommentCountsReqDTO.builder().videoId(videoDO.getId()).build();
                GetVideoCommentCountsRespDTO getVideoCommentCountsRespDTO = commentRpcService.getVideoCommentCounts(getVideoCommentCountsReqDTO);
                return getVideoCommentCountsRespDTO.getCommentCounts();
            });

            try {
                likeCounts = likeCountsFuture.get(4, TimeUnit.SECONDS);
                commentCounts = commentCountsFuture.get(4, TimeUnit.SECONDS);
            } catch (Exception e) {
                throw new RemoteException(e.getMessage());
            }

            videoInfoDTO.setLikeCounts(likeCounts);
            videoInfoDTO.setCommentCounts(commentCounts);
            videoInfoDTOList.add(videoInfoDTO);
        }

        GetWorkCountRespDTO workCountRespDTO = this.getWorkCount(GetWorkCountReqDTO.builder().userId(requestParam.getUserId()).build());

        respDTO.setVideoList(videoInfoDTOList);
        respDTO.setPage(requestParam.getPage());
        respDTO.setTotalPage((workCountRespDTO.getWorkCount() / requestParam.getPageSize() > 0 ? workCountRespDTO.getWorkCount() / requestParam.getPageSize() : 1));
        respDTO.setTotalCount(workCountRespDTO.getWorkCount());

        return respDTO;
    }

    @Override
    public GetWorkCountRespDTO getWorkCount(GetWorkCountReqDTO requestParam) {
        this.checkParam(requestParam);

        // 作者不存在直接返回空
        if (!userIdCachePenetrationBloomFilter.contains(requestParam.getUserId())) {
            // TODO: 如果作者不存在，是否需要返回错误信息？
            return GetWorkCountRespDTO.builder().code(SUCCESS_CODE).message(SUCCESS_MESSAGE).workCount(0).build();
        }

        GetWorkCountRespDTO respDTO = GetWorkCountRespDTO.builder().code(SUCCESS_CODE).message(SUCCESS_MESSAGE).build();
        Integer workCount = workCountLocalCache.getIfPresent(requestParam.getUserId());
        if (workCount != null) {
            respDTO.setWorkCount(workCount);
            return respDTO;
        }

        workCount = (Integer) redisTemplate.opsForValue().get(String.format(GET_WORKCOUNT_BY_AUTHORID_KEY, requestParam.getUserId()));
        if (workCount != null) {
            respDTO.setWorkCount(workCount);
            return respDTO;
        }

        RLock lock = redissonClient.getLock(String.format(LOCK_GET_WORKCOUNT_BY_AUTHORID_KEY, requestParam.getUserId()));
        lock.lock();
        try {
            workCount = (Integer) redisTemplate.opsForValue().get(String.format(GET_WORKCOUNT_BY_AUTHORID_KEY, requestParam.getUserId()));
            if (workCount != null) {
                respDTO.setWorkCount(workCount);
                return respDTO;
            }

            LambdaQueryWrapper<VideoDO> queryWrapper = Wrappers.lambdaQuery(VideoDO.class)
                    .eq(VideoDO::getAuthorId, requestParam.getUserId());
            workCount = baseMapper.selectCount(queryWrapper).intValue();

            redisTemplate.opsForValue().set(
                    String.format(GET_WORKCOUNT_BY_AUTHORID_KEY, requestParam.getUserId()),
                    workCount,
                    GET_WORKCOUNT_BASE_TTL + ThreadLocalRandom.current().nextInt(31), TimeUnit.SECONDS);
            workCountLocalCache.put(requestParam.getUserId(), workCount);

            respDTO.setWorkCount(workCount);
        } finally {
            lock.unlock();
        }

        return respDTO;
    }


    public void checkParam(PublishReqDTO requestParam) {
        if (requestParam == null) {
            throw new ClientException(REQUEST_PARAM_NULL);
        }

        if (requestParam.getUserId() == null) {
            throw new ClientException(USER_ID_NULL);
        }

        VideoInfoDTO videoInfoDTO = requestParam.getVideoInfo();
        if (videoInfoDTO == null) {
            throw new ClientException(VIDEO_INFO_NULL);
        }
    }

    public void checkParam(GetPublishListReqDTO requestParam) {
        if (requestParam == null) {
            throw new ClientException(REQUEST_PARAM_NULL);
        }

        if (requestParam.getUserId() == null) {
            throw new ClientException(USER_ID_NULL);
        }

        if (requestParam.getPage() == null) {
            log.warn("[getWorkCount][checkParam] page is null, set default value: {}; req={}", COMMON_START_PAGE, requestParam);
            requestParam.setPage(COMMON_START_PAGE);
        }
        if (requestParam.getPageSize() == null) {
            log.warn("[getWorkCount][checkParam] pageSize is null, set default value: {}; req={}", COMMON_PAGE_SIZE, requestParam);
            requestParam.setPageSize(COMMON_PAGE_SIZE);
        }

        if (requestParam.getIsPrivate() == null) {
            throw new ClientException(IS_PRIVATE_NULL);
        }
    }

    public void checkParam(GetWorkCountReqDTO requestParam) {
        if (requestParam == null) {
            throw new ClientException(REQUEST_PARAM_NULL);
        }
        if (requestParam.getUserId() == null) {
            throw new ClientException(USER_ID_NULL);
        }
    }

    public void addVideoToLocalCache(Long userId, VideoDO newVideo) {
        String key = userId + ":" + newVideo.getIsPrivate();
        List<VideoDO> currentList = publishListLocalCache.getIfPresent(key);
        if (currentList == null) {
            currentList = new ArrayList<>();
        }

        currentList.add(newVideo);

        // 按创建时间降序排序
        currentList.sort((a, b) -> Long.compare(b.getCreateTime().getTime(), a.getCreateTime().getTime()));

        publishListLocalCache.put(key, currentList);
    }

    public List<VideoDO> getBasePublishList(GetPublishListReqDTO requestParam) {
        List<VideoDO> videoDOList = getPublishListFromLocalCache(requestParam);
        if (CollectionUtil.isNotEmpty(videoDOList)) {
            return videoDOList;
        }

        videoDOList = getPublishListFromRedis(requestParam);
        if (CollectionUtil.isNotEmpty(videoDOList)) {
            return videoDOList;
        }

        RLock lock = redissonClient.getLock(String.format(LOCK_GET_PUBLISH_LIST_BY_AUTHORID_KEY, requestParam.getUserId(), requestParam.getIsPrivate()));
        lock.lock();
        try {
            videoDOList = getPublishListFromRedis(requestParam);
            if (CollectionUtil.isNotEmpty(videoDOList)) {
                return videoDOList;
            }

            Page<VideoDO> pageReq = new Page<>(requestParam.getPage(), requestParam.getPageSize());
            LambdaQueryWrapper<VideoDO> queryWrapper = Wrappers.lambdaQuery(VideoDO.class)
                    .eq(VideoDO::getAuthorId, requestParam.getUserId())
                    .eq(VideoDO::getIsPrivate, requestParam.getIsPrivate())
                    .orderByDesc(VideoDO::getCreateTime);
            videoDOList = baseMapper.selectPage(pageReq, queryWrapper).getRecords();

            // page从小到大逐渐增加
            // 缓存命中最高的是前几页，深分页大多不会命中
            // Redis只对第一、二页做缓存。可调整
            if (requestParam.getPage() <= 2) {
                String redisKey = String.format(GET_PUBLISH_LIST_KEY, requestParam.getUserId(), requestParam.getIsPrivate());
                Set<ZSetOperations.TypedTuple<String>> tuples = videoDOList.stream()
                        .map(video -> new DefaultTypedTuple<>(
                                JSON.toJSONString(video),
                                (double) video.getCreateTime().getTime()
                        ))
                        .collect(Collectors.toSet());
                redisTemplate.opsForZSet().add(redisKey, tuples);
                redisTemplate.expire(
                        String.format(GET_PUBLISH_LIST_KEY, requestParam.getUserId(), requestParam.getIsPrivate()),
                        GET_PUBLISH_LIST_BASE_TTL + ThreadLocalRandom.current().nextInt(31),
                        TimeUnit.SECONDS
                );
            }

            // LocalCache只对第一页做缓存
            if (requestParam.getPage() <= 1) {
                publishListLocalCache.put(requestParam.getUserId() + ":" + requestParam.getIsPrivate(), videoDOList);
            }
        } finally {
            lock.unlock();
        }

        return videoDOList;
    }

    public List<VideoDO> getPublishListFromLocalCache(GetPublishListReqDTO requestParam) {
        String key = requestParam.getUserId() + ":" + requestParam.getPage();

        List<VideoDO> fullList = publishListLocalCache.getIfPresent(key);
        if (CollectionUtil.isEmpty(fullList)) {
            return Collections.emptyList();
        }

        int start = (requestParam.getPage() - 1) * requestParam.getPageSize();
        int end = Math.min(start + requestParam.getPageSize(), fullList.size());

        if (start >= fullList.size()) {
            return Collections.emptyList(); // 页码过大，返回空
        }

        return fullList.subList(start, end);
    }

    public List<VideoDO> getPublishListFromRedis(GetPublishListReqDTO requestParam) {
        String redisKey = String.format(GET_PUBLISH_LIST_KEY, requestParam.getUserId(), requestParam.getIsPrivate());
        int start = (requestParam.getPage() - 1) * requestParam.getPageSize();
        int end = start + requestParam.getPageSize() - 1;

        Set<String> rawSet = redisTemplate.opsForZSet().reverseRange(redisKey, start, end);

        if (CollectionUtils.isEmpty(rawSet)) {
            return Collections.emptyList();
        }

        return rawSet.stream()
                .map(json -> JSON.parseObject(json, VideoDO.class))
                .collect(Collectors.toList());
    }
}
