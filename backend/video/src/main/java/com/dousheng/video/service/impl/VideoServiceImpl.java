package com.dousheng.video.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dousheng.dto.common.UserInfoDTO;
import com.dousheng.dto.common.VideoInfoDTO;
import com.dousheng.dto.req.comment.GetVideoCommentCountsReqDTO;
import com.dousheng.dto.req.favorite.GetVideoLikeCountsReqDTO;
import com.dousheng.dto.req.favorite.IsLikeVideoReqDTO;
import com.dousheng.dto.req.relation.IsFollowReqDTO;
import com.dousheng.dto.req.user.GetUserInfoReqDTO;
import com.dousheng.dto.req.video.*;
import com.dousheng.dto.resp.comment.GetVideoCommentCountsRespDTO;
import com.dousheng.dto.resp.favorite.GetVideoLikeCountsRespDTO;
import com.dousheng.dto.resp.favorite.IsLikeVideoRespDTO;
import com.dousheng.dto.resp.relation.IsFollowRespDTO;
import com.dousheng.dto.resp.user.GetUserInfoRespDTO;
import com.dousheng.dto.resp.video.*;
import com.dousheng.service.CommentRpcService;
import com.dousheng.service.FavoriteRpcService;
import com.dousheng.service.RelationRpcService;
import com.dousheng.service.UserRpcService;
import com.dousheng.video.common.convention.exception.ClientException;
import com.dousheng.video.common.convention.exception.RemoteException;
import com.dousheng.video.common.convention.exception.ServiceException;
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
import org.springframework.context.annotation.Bean;
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
import static com.dousheng.video.common.enums.YesOrNoEnum.YES;

@Service
@RequiredArgsConstructor
public class VideoServiceImpl extends ServiceImpl<VideoMapper, VideoDO> implements VideoService {

    @DubboReference(timeout = 4000, retries = 3, loadbalance = "roundrobin")
    private UserRpcService userRpcService;
    @DubboReference(timeout = 4000, retries = 3, loadbalance = "roundrobin")
    private FavoriteRpcService favoriteRpcService;
    @DubboReference(timeout = 4000, retries = 3, loadbalance = "roundrobin")
    private RelationRpcService relationRpcService;
    @DubboReference(timeout = 4000, retries = 3, loadbalance = "roundrobin")
    private CommentRpcService commentRpcService;

    private final RedissonClient redissonClient;
    private final RedisTemplate redisTemplate;
    private final RocketMQTemplate rocketMQTemplate;
    private final Cache<Long, List<VideoDO>> publicListLocalCache;
    private final Cache<Long, VideoDO> videoDetailLocalCache;
    private final Cache<Long, Integer> workCountLocalCache;
    private final RBloomFilter<Long> userIdCachePenetrationBloomFilter;
    private final RBloomFilter<Long> videoIdCachePenetrationBloomFilter;

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
                String.format(GET_PUBLIC_LIST_KEY, requestParam.getUserId()),
                JSON.toJSONString(videoDO),
                videoDO.getCreateTime().getTime()
        );
        redisTemplate.expire(
                String.format(GET_PUBLIC_LIST_KEY, requestParam.getUserId()),
                GET_PUBLIC_LIST_BASE_TTL + ThreadLocalRandom.current().nextInt(31),
                TimeUnit.SECONDS
        );
        addVideoToLocalCache(requestParam.getUserId(), videoDO);

        redisTemplate.opsForValue().set(
                String.format(GET_VIDEO_DETAIL_BY_VIDEOID_KEY, videoDO.getId()),
                JSON.toJSONString(videoDO),
                GET_VIDEO_DETAIL_BASE_TTL + ThreadLocalRandom.current().nextInt(31),
                TimeUnit.SECONDS);
        videoDetailLocalCache.put(videoDO.getId(), videoDO);

        redisTemplate.delete(String.format(GET_WORKCOUNT_BY_AUTHORID_KEY, requestParam.getUserId()));
        workCountLocalCache.invalidate(requestParam.getUserId());

        userIdCachePenetrationBloomFilter.add(requestParam.getUserId());
        videoIdCachePenetrationBloomFilter.add(videoDO.getId());

        VideoInfoDTO videoInfoDTO = BeanUtil.copyProperties(videoDO, VideoInfoDTO.class);
        videoInfoDTO.setLikeCounts(0);
        videoInfoDTO.setCommentCounts(0);
        rocketMQTemplate.syncSend(HOT_VIDEO_TOPIC, videoInfoDTO);

        return PublishRespDTO.builder()
                .code(SUCCESS_CODE)
                .message(SUCCESS_MESSAGE)
                .build();
    }

    @Override
    public ChangeVideoToPrivateRespDTO changeVideoToPrivate(ChangeVideoToPrivateReqDTO requestParam) {
        this.checkParam(requestParam);

        VideoDO videoDO = VideoDO.builder().isPrivate(YES.type).build();
        LambdaUpdateWrapper<VideoDO> updateWrapper = Wrappers.lambdaUpdate(VideoDO.class)
                .eq(VideoDO::getId, requestParam.getVideoId())
                .eq(VideoDO::getAuthorId, requestParam.getUserId());
        baseMapper.update(videoDO, updateWrapper);

        redisTemplate.delete(String.format(GET_PUBLIC_LIST_KEY, requestParam.getUserId()));
        publicListLocalCache.invalidate(requestParam.getUserId());

        redisTemplate.delete(String.format(GET_VIDEO_DETAIL_BY_VIDEOID_KEY, requestParam.getVideoId()));
        videoDetailLocalCache.invalidate(requestParam.getVideoId());

        return ChangeVideoToPrivateRespDTO.builder().
                code(SUCCESS_CODE).
                message(SUCCESS_MESSAGE).
                build();
    }

    @Override
    public ChangeVideoToPublicRespDTO changeVideoToPublic(ChangeVideoToPublicReqDTO requestParam) {
        this.checkParam(requestParam);

        VideoDO videoDO = VideoDO.builder().isPrivate(NO.type).build();
        LambdaUpdateWrapper<VideoDO> updateWrapper = Wrappers.lambdaUpdate(VideoDO.class)
                .eq(VideoDO::getId, requestParam.getVideoId())
                .eq(VideoDO::getAuthorId, requestParam.getUserId());
        baseMapper.update(videoDO, updateWrapper);

        redisTemplate.delete(String.format(GET_PUBLIC_LIST_KEY, requestParam.getUserId()));
        publicListLocalCache.invalidate(requestParam.getUserId());

        redisTemplate.delete(String.format(GET_VIDEO_DETAIL_BY_VIDEOID_KEY, requestParam.getVideoId()));
        videoDetailLocalCache.invalidate(requestParam.getVideoId());

        return ChangeVideoToPublicRespDTO.builder().
                code(SUCCESS_CODE).
                message(SUCCESS_MESSAGE).
                build();
    }

    @Override
    public GetPublicListRespDTO getPublicList(GetPublicListReqDTO requestParam) {
        this.checkParam(requestParam);

        GetPublicListRespDTO respDTO = GetPublicListRespDTO.builder().code(SUCCESS_CODE).message(SUCCESS_MESSAGE).build();

        if (!userIdCachePenetrationBloomFilter.contains(requestParam.getUserId())) {
           throw new ServiceException(AUTHOR_NOT_EXIST);
        }

        List<VideoDO> videoDOList = this.getBasePublicList(requestParam);

        List<VideoInfoDTO> videoInfoDTOList = new ArrayList<>();
        for (int i = 0; i < videoDOList.size(); i++) {
            VideoDO videoDO = videoDOList.get(i);
            if (videoDO.getIsPrivate().equals(YES.type)) {
                continue; // 跳过私有视频
            }
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
    public GetPrivateListRespDTO getPrivateList(GetPrivateListReqDTO requestParam) {
        this.checkParam(requestParam);

        Page<VideoDO> pageReq = new Page<>(requestParam.getPage(), requestParam.getPageSize());
        LambdaQueryWrapper<VideoDO> queryWrapper = Wrappers.lambdaQuery(VideoDO.class)
                .eq(VideoDO::getAuthorId, requestParam.getUserId())
                .eq(VideoDO::getIsPrivate, requestParam.getIsPrivate())
                .orderByDesc(VideoDO::getCreateTime);
        List<VideoDO> videoDOList = baseMapper.selectPage(pageReq, queryWrapper).getRecords();

        List<VideoInfoDTO> videoInfoDTOList = BeanUtil.copyToList(videoDOList, VideoInfoDTO.class);
        for (VideoInfoDTO videoInfoDTO : videoInfoDTOList) {
            if (videoInfoDTO.getIsPrivate().equals(NO.type)) {
                continue; // 跳过非私有视频
            }

            Integer likeCounts, commentCounts = 0;

            Future<Integer> likeCountsFuture = executorService.submit(() -> {
                GetVideoLikeCountsReqDTO getVideoLikeCountsReqDTO = GetVideoLikeCountsReqDTO.builder().videoId(videoInfoDTO.getId()).build();
                GetVideoLikeCountsRespDTO getVideoLikeCountsRespDTO = favoriteRpcService.getVideoLikeCounts(getVideoLikeCountsReqDTO);
                return getVideoLikeCountsRespDTO.getLikeCounts();
            });
            Future<Integer> commentCountsFuture = executorService.submit(() -> {
                GetVideoCommentCountsReqDTO getVideoCommentCountsReqDTO = GetVideoCommentCountsReqDTO.builder().videoId(videoInfoDTO.getId()).build();
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
        }

        return GetPrivateListRespDTO.builder().
                code(SUCCESS_CODE).
                message(SUCCESS_MESSAGE).
                page(requestParam.getPage()).
                totalPage((int) pageReq.getPages()).
                totalCount((int) pageReq.getTotal()).
                videoList(videoInfoDTOList).
                build();
    }

    @Override
    public GetVideoDetailRespDTO getVideoDetail(GetVideoDetailReqDTO requestParam) {
        this.checkParam(requestParam);

        if (!videoIdCachePenetrationBloomFilter.contains(requestParam.getVideoId())) {
            throw new ServiceException(VIDEO_NOT_EXIST);
        }

        VideoDO videoDO = this.getBaseVideoDetail(requestParam);
        if (videoDO.getIsPrivate().equals(YES.type) && !requestParam.getUserId().equals(videoDO.getAuthorId())) {
            throw new ServiceException(NO_PERMISSION);
        }
        VideoInfoDTO videoInfoDTO = BeanUtil.copyProperties(videoDO, VideoInfoDTO.class);

        Boolean isFavorite = false, isFollow = false;
        Integer likeCounts = 0, commentCounts = 0;

        GetUserInfoRespDTO getUserInfoRespDTO = userRpcService.getUserInfo(GetUserInfoReqDTO.builder().userId(videoInfoDTO.getAuthorId()).build());
        UserInfoDTO author = getUserInfoRespDTO.getUserInfo();
        videoInfoDTO.setAuthorName(author.getName());
        videoInfoDTO.setAuthorAvatar(author.getAvatar());

        Future<Boolean> isFavoriteFuture = executorService.submit(() -> {
            IsLikeVideoReqDTO isLikeVideoReqDTO = IsLikeVideoReqDTO.builder().userId(requestParam.getUserId()).videoId(videoInfoDTO.getId()).build();
            IsLikeVideoRespDTO isLikeVideoRespDTO = favoriteRpcService.isLikeVideo(isLikeVideoReqDTO);
            return isLikeVideoRespDTO.getIsLike();
        });
        Future<Boolean> isFollowFuture = executorService.submit(() -> {
            IsFollowReqDTO isFollowReqDTO = IsFollowReqDTO.builder().fromUserId(requestParam.getUserId()).toUserId(videoInfoDTO.getAuthorId()).build();
            IsFollowRespDTO isFollowRespDTO = relationRpcService.isFollow(isFollowReqDTO);
            return isFollowRespDTO.getIsFollow();
        });
        Future<Integer> likeCountsFuture = executorService.submit(() -> {
            GetVideoLikeCountsReqDTO getVideoLikeCountsReqDTO = GetVideoLikeCountsReqDTO.builder().videoId(videoInfoDTO.getId()).build();
            GetVideoLikeCountsRespDTO getVideoLikeCountsRespDTO = favoriteRpcService.getVideoLikeCounts(getVideoLikeCountsReqDTO);
            return getVideoLikeCountsRespDTO.getLikeCounts();
        });
        Future<Integer> commentCountsFuture = executorService.submit(() -> {
            GetVideoCommentCountsReqDTO getVideoCommentCountsReqDTO = GetVideoCommentCountsReqDTO.builder().videoId(videoInfoDTO.getId()).build();
            GetVideoCommentCountsRespDTO getVideoCommentCountsRespDTO = commentRpcService.getVideoCommentCounts(getVideoCommentCountsReqDTO);
            return getVideoCommentCountsRespDTO.getCommentCounts();
        });

        try {
            isFavorite = isFavoriteFuture.get(4, TimeUnit.SECONDS);
            isFollow = isFollowFuture.get(4, TimeUnit.SECONDS);
            likeCounts = likeCountsFuture.get(4, TimeUnit.SECONDS);
            commentCounts = commentCountsFuture.get(4, TimeUnit.SECONDS);
        } catch (Exception e) {
            throw new RemoteException(e.getMessage());
        }

        videoInfoDTO.setIsFavorite(isFavorite);
        videoInfoDTO.setIsFollow(isFollow);
        videoInfoDTO.setLikeCounts(likeCounts);
        videoInfoDTO.setCommentCounts(commentCounts);

        return GetVideoDetailRespDTO.builder()
                .code(SUCCESS_CODE)
                .message(SUCCESS_MESSAGE)
                .videoInfo(videoInfoDTO)
                .build();
    }

    @Override
    public GetVideoDetailListRespDTO getVideoDetailList(GetVideoDetailListReqDTO requestParam) {
        this.checkParam(requestParam);

        List<VideoInfoDTO> videoInfoDTOList = new ArrayList<>();
        for (int i = 0; i < requestParam.getVideoIdList().size(); i++) {
            Long videoId = requestParam.getVideoIdList().get(i);

            GetVideoDetailReqDTO getVideoDetailReqDTO = GetVideoDetailReqDTO.builder().
                    userId(requestParam.getUserId()).
                    videoId(videoId).
                    build();

            GetVideoDetailRespDTO getVideoDetailRespDTO = this.getVideoDetail(getVideoDetailReqDTO);

            videoInfoDTOList.add(getVideoDetailRespDTO.getVideoInfo());
        }

        return GetVideoDetailListRespDTO.builder().
                code(SUCCESS_CODE).
                message(SUCCESS_MESSAGE).
                videoInfoList(videoInfoDTOList).
                build();
    }

    @Override
    public GetWorkCountRespDTO getWorkCount(GetWorkCountReqDTO requestParam) {
        this.checkParam(requestParam);

        if (!userIdCachePenetrationBloomFilter.contains(requestParam.getUserId())) {
            throw new ServiceException(AUTHOR_NOT_EXIST);
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

    public void checkParam(GetPublicListReqDTO requestParam) {
        if (requestParam == null) {
            throw new ClientException(REQUEST_PARAM_NULL);
        }

        if (requestParam.getUserId() == null) {
            throw new ClientException(USER_ID_NULL);
        }

        if (requestParam.getPage() == null || requestParam.getPageSize() == null) {
            throw new ClientException(PAGE_PARAM_NULL);
        }

        if (requestParam.getIsPrivate() == null) {
            throw new ClientException(IS_PRIVATE_NULL);
        }

        if (requestParam.getIsPrivate().equals(YES.type)) {
            throw new ClientException(NO_PERMISSION);
        }
    }

    public void checkParam(GetVideoDetailReqDTO requestParam) {
        if (requestParam == null) {
            throw new ClientException(REQUEST_PARAM_NULL);
        }
        if (requestParam.getUserId() == null) {
            throw new ClientException(USER_ID_NULL);
        }
        if (requestParam.getVideoId() == null) {
            throw new ClientException(VIDEO_ID_NULL);
        }
    }

    public void checkParam(GetVideoDetailListReqDTO requestParam) {
        if (requestParam == null) {
            throw new ClientException(REQUEST_PARAM_NULL);
        }
        if (requestParam.getUserId() == null) {
            throw new ClientException(USER_ID_NULL);
        }
        if (CollectionUtil.isEmpty(requestParam.getVideoIdList())) {
            throw new ClientException(VIDEO_ID_LIST_NULL);
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

    public void checkParam(ChangeVideoToPrivateReqDTO requestParam) {
        if (requestParam == null) {
            throw new ClientException(REQUEST_PARAM_NULL);
        }
        if (requestParam.getUserId() == null) {
            throw new ClientException(USER_ID_NULL);
        }
        if (requestParam.getVideoId() == null) {
            throw new ClientException(VIDEO_ID_NULL);
        }
    }

    public void checkParam(ChangeVideoToPublicReqDTO requestParam) {
        if (requestParam == null) {
            throw new ClientException(REQUEST_PARAM_NULL);
        }
        if (requestParam.getUserId() == null) {
            throw new ClientException(USER_ID_NULL);
        }
        if (requestParam.getVideoId() == null) {
            throw new ClientException(VIDEO_ID_NULL);
        }
    }

    public void checkParam(GetPrivateListReqDTO requestParam) {
        if (requestParam == null) {
            throw new ClientException(REQUEST_PARAM_NULL);
        }
        if (requestParam.getUserId() == null) {
            throw new ClientException(USER_ID_NULL);
        }
        if (requestParam.getPage() == null || requestParam.getPageSize() == null) {
            throw new ClientException(PAGE_PARAM_NULL);
        }
        if (requestParam.getIsPrivate() == null) {
            throw new ClientException(IS_PRIVATE_NULL);
        }
        if (requestParam.getIsPrivate().equals(NO.type)) {
            throw new ClientException(NO_PERMISSION);
        }
    }

    public void addVideoToLocalCache(Long userId, VideoDO newVideo) {
        List<VideoDO> currentList = publicListLocalCache.getIfPresent(userId);
        if (currentList == null) {
            currentList = new ArrayList<>();
        }

        currentList.add(newVideo);

        // 按创建时间降序排序
        currentList.sort((a, b) -> Long.compare(b.getCreateTime().getTime(), a.getCreateTime().getTime()));

        publicListLocalCache.put(userId, currentList);
    }

    public List<VideoDO> getBasePublicList(GetPublicListReqDTO requestParam) {
        List<VideoDO> videoDOList = getPublicListFromLocalCache(requestParam);
        if (CollectionUtil.isNotEmpty(videoDOList)) {
            return videoDOList;
        }

        videoDOList = getPublicListFromRedis(requestParam);
        if (CollectionUtil.isNotEmpty(videoDOList)) {
            return videoDOList;
        }

        RLock lock = redissonClient.getLock(String.format(LOCK_GET_PUBLIC_LIST_BY_AUTHORID_KEY, requestParam.getUserId()));
        lock.lock();
        try {
            videoDOList = getPublicListFromRedis(requestParam);
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
                Set<ZSetOperations.TypedTuple<String>> tuples = videoDOList.stream()
                        .map(video -> new DefaultTypedTuple<>(
                                JSON.toJSONString(video),
                                (double) video.getCreateTime().getTime()
                        ))
                        .collect(Collectors.toSet());
                redisTemplate.opsForZSet().add(String.format(GET_PUBLIC_LIST_KEY, requestParam.getUserId()), tuples);
                redisTemplate.expire(
                        String.format(GET_PUBLIC_LIST_KEY, requestParam.getUserId()),
                        GET_PUBLIC_LIST_BASE_TTL + ThreadLocalRandom.current().nextInt(31),
                        TimeUnit.SECONDS
                );
            }

            // LocalCache只对第一页做缓存
            if (requestParam.getPage() <= 1) {
                publicListLocalCache.put(requestParam.getUserId(), videoDOList);
            }
        } finally {
            lock.unlock();
        }

        return videoDOList;
    }

    public List<VideoDO> getPublicListFromLocalCache(GetPublicListReqDTO requestParam) {
        List<VideoDO> fullList = publicListLocalCache.getIfPresent(requestParam.getUserId());
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

    public List<VideoDO> getPublicListFromRedis(GetPublicListReqDTO requestParam) {
        int start = (requestParam.getPage() - 1) * requestParam.getPageSize();
        int end = start + requestParam.getPageSize() - 1;

        Set<String> rawSet = redisTemplate.opsForZSet().reverseRange(String.format(GET_PUBLIC_LIST_KEY, requestParam.getUserId()), start, end);

        if (CollectionUtils.isEmpty(rawSet)) {
            return Collections.emptyList();
        }

        return rawSet.stream()
                .map(json -> JSON.parseObject(json, VideoDO.class))
                .collect(Collectors.toList());
    }

    public VideoDO getBaseVideoDetail(GetVideoDetailReqDTO requestParam) {
        VideoDO videoDO = videoDetailLocalCache.getIfPresent(requestParam.getVideoId());
        if (videoDO != null) {
            return videoDO;
        }

        String videoJsonStr = (String) redisTemplate.opsForValue().get(String.format(GET_VIDEO_DETAIL_BY_VIDEOID_KEY, requestParam.getVideoId()));
        if (StrUtil.isNotBlank(videoJsonStr)) {
            videoDO = JSON.parseObject(videoJsonStr, VideoDO.class);
            return videoDO;
        }

        RLock lock = redissonClient.getLock(String.format(LOCK_GET_VIDEO_DETAIL_BY_VIDEOID_KEY, requestParam.getVideoId()));
        lock.lock();
        try {
            videoJsonStr = (String) redisTemplate.opsForValue().get(String.format(GET_VIDEO_DETAIL_BY_VIDEOID_KEY, requestParam.getVideoId()));
            if (StrUtil.isNotBlank(videoJsonStr)) {
                videoDO = JSON.parseObject(videoJsonStr, VideoDO.class);
                return videoDO;
            }

            LambdaQueryWrapper<VideoDO> queryWrapper = Wrappers.lambdaQuery(VideoDO.class)
                    .eq(VideoDO::getId, requestParam.getVideoId());
            videoDO = baseMapper.selectOne(queryWrapper);
            if (videoDO == null) {
                throw new ServiceException(VIDEO_NOT_EXIST);
            }

            redisTemplate.opsForValue().set(
                    String.format(GET_VIDEO_DETAIL_BY_VIDEOID_KEY, videoDO.getId()),
                    JSON.toJSONString(videoDO),
                    GET_VIDEO_DETAIL_BASE_TTL + ThreadLocalRandom.current().nextInt(31),
                    TimeUnit.SECONDS);
            videoDetailLocalCache.put(videoDO.getId(), videoDO);

        } finally {
            lock.unlock();
        }

        return videoDO;
    }
}
