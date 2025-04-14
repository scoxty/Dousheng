package com.dousheng.feed.task;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.dousheng.dto.common.VideoInfoDTO;
import com.dousheng.dto.req.video.GetVideoDetailListReqDTO;
import com.dousheng.dto.req.video.GetVideoDetailReqDTO;
import com.dousheng.dto.resp.video.GetVideoDetailListRespDTO;
import com.dousheng.dto.resp.video.GetVideoDetailRespDTO;
import com.dousheng.feed.toolkit.FeedScoreCalculator;
import com.dousheng.service.VideoRpcService;
import lombok.RequiredArgsConstructor;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.dousheng.feed.common.constant.RedisCacheConstant.GLOBAL_HOT_VIDEO_KEY;
import static com.dousheng.feed.common.constant.SuccessBaseRespConstant.SUCCESS_CODE;

@Component
@RequiredArgsConstructor
public class FeedHotScoreRefreshScheduler {

    private static final Log log = LogFactory.get();

    private final RedisTemplate redisTemplate;
    @DubboReference(timeout = 4000, retries = 3, loadbalance = "roundrobin")
    private VideoRpcService videoRpcService;

    @Scheduled(cron = "0 */30 * * * ?") // 每30分钟执行
    public void refreshHotVideoScore() {
        Long userId = -1L; // 访客用户ID
        Set<Object> rawSet = redisTemplate.opsForZSet().range(GLOBAL_HOT_VIDEO_KEY, 0, -1);
        if (CollectionUtil.isEmpty(rawSet)) {
            log.error("[task][refreshHotVideoScore] global hot video set is null");
            return;
        }

        List<Long> videoIds = rawSet.stream().map(Object::toString).map(Long::parseLong).toList();
        GetVideoDetailListReqDTO reqDTO = GetVideoDetailListReqDTO.builder().userId(userId).videoIdList(videoIds).build();
        GetVideoDetailListRespDTO respDTO = videoRpcService.getVideoDetailList(reqDTO);
        if (!SUCCESS_CODE.equals(respDTO.getCode())) {
            log.error("[task][refreshHotVideoScore] get video detail list failed, req={}, resp={}", reqDTO, respDTO);
            return;
        }

        List<VideoInfoDTO> videoList = respDTO.getVideoInfoList();
        for (VideoInfoDTO video : videoList) {
            redisTemplate.opsForZSet().add(GLOBAL_HOT_VIDEO_KEY, video.getId(), FeedScoreCalculator.calculate(video));
        }
    }
}
