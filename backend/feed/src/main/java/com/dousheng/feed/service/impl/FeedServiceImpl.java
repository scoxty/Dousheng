package com.dousheng.feed.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import com.dousheng.dto.common.UserInfoDTO;
import com.dousheng.dto.common.VideoInfoDTO;
import com.dousheng.dto.req.comment.GetVideoCommentCountsReqDTO;
import com.dousheng.dto.req.favorite.GetVideoLikeCountsReqDTO;
import com.dousheng.dto.req.favorite.IsLikeVideoReqDTO;
import com.dousheng.dto.req.feed.GetIndexFeedReqDTO;
import com.dousheng.dto.req.relation.IsFollowReqDTO;
import com.dousheng.dto.req.user.GetUserInfoReqDTO;
import com.dousheng.dto.req.video.GetVideoDetailListReqDTO;
import com.dousheng.dto.resp.comment.GetVideoCommentCountsRespDTO;
import com.dousheng.dto.resp.favorite.GetVideoLikeCountsRespDTO;
import com.dousheng.dto.resp.favorite.IsLikeVideoRespDTO;
import com.dousheng.dto.resp.feed.GetIndexFeedRespDTO;
import com.dousheng.dto.resp.relation.IsFollowRespDTO;
import com.dousheng.dto.resp.user.GetUserInfoRespDTO;
import com.dousheng.dto.resp.video.GetVideoDetailListRespDTO;
import com.dousheng.feed.common.convention.exception.RemoteException;
import com.dousheng.feed.common.convention.exception.ServiceException;
import com.dousheng.feed.service.FeedService;
import com.dousheng.service.*;
import lombok.RequiredArgsConstructor;
import org.apache.dubbo.config.annotation.DubboReference;
import org.redisson.api.RBloomFilter;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.*;
import java.util.stream.Collectors;

import static com.dousheng.feed.common.constant.RedisCacheConstant.GLOBAL_HOT_VIDEO_KEY;
import static com.dousheng.feed.common.constant.SuccessBaseRespConstant.SUCCESS_CODE;
import static com.dousheng.feed.common.constant.SuccessBaseRespConstant.SUCCESS_MESSAGE;
import static com.dousheng.feed.common.enums.FeedErrorCodeEnum.FEED_LIST_EMPTY;

@Service
@RequiredArgsConstructor
public class FeedServiceImpl implements FeedService {

    @DubboReference(timeout = 5000, retries = 3, loadbalance = "roundrobin")
    private VideoRpcService videoRpcService;

    private final RedisTemplate redisTemplate;
    private final RBloomFilter<Long> videoIdCachePenetrationBloomFilter;

    private final ExecutorService executorService = new ThreadPoolExecutor(
            Runtime.getRuntime().availableProcessors() << 1,
            Runtime.getRuntime().availableProcessors() << 2,
            60L,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(200),
            new ThreadPoolExecutor.CallerRunsPolicy());

    /**
     * 暂时没接入推荐服务，因此这里直接从全局热点池中获取
     * 待接入推荐服务后，可以为每个用户分配一个feed池
     */
    @Override
    public GetIndexFeedRespDTO getIndexFeed(GetIndexFeedReqDTO requestParam) {
        int start = (requestParam.getPage() - 1) * requestParam.getPageSize();
        int end = start + requestParam.getPageSize() - 1;
        Set<Object> rawSet = redisTemplate.opsForZSet().reverseRange(GLOBAL_HOT_VIDEO_KEY, start, end);
        Integer count = Objects.requireNonNull(redisTemplate.opsForZSet().zCard(GLOBAL_HOT_VIDEO_KEY)).intValue();
        if (CollectionUtil.isEmpty(rawSet)) {
            throw new ServiceException(FEED_LIST_EMPTY);
        }
        List<Long> filteredHotVideoIds = rawSet.stream()
                .map(o -> Long.valueOf(o.toString()))
//                .filter(videoIdCachePenetrationBloomFilter::contains) // 测试阶段暂时注释掉
                .toList();

        Long userId = requestParam.getUserId() == null ? -1 : requestParam.getUserId();
        GetVideoDetailListReqDTO videoDetailListReqDTO = GetVideoDetailListReqDTO.builder()
               .userId(userId).
                videoIdList(filteredHotVideoIds).
                build();
        GetVideoDetailListRespDTO getVideoDetailListRespDTO = videoRpcService.getVideoDetailList(videoDetailListReqDTO);
        if (!SUCCESS_CODE.equals(getVideoDetailListRespDTO.getCode())) {
            throw new RemoteException(getVideoDetailListRespDTO.getMessage());
        }

        // 测试阶段暂时注释掉
//        getVideoDetailListRespDTO.getVideoInfoList().forEach(video -> {
//            videoIdCachePenetrationBloomFilter.add(video.getId());
//        });

        return GetIndexFeedRespDTO.builder().
                code(SUCCESS_CODE).
                message(SUCCESS_MESSAGE).
                page(requestParam.getPage()).
                totalPage((count / requestParam.getPageSize() > 0 ? count / requestParam.getPageSize() : 1)).
                totalCount(count).
                videoList(getVideoDetailListRespDTO.getVideoInfoList()).
                build();
    }


}
