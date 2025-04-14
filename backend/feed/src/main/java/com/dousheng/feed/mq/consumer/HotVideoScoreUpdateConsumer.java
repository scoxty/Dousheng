package com.dousheng.feed.mq.consumer;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.alibaba.fastjson2.JSON;
import com.dousheng.dto.common.VideoInfoDTO;
import com.dousheng.dto.req.video.GetVideoDetailListReqDTO;
import com.dousheng.dto.req.video.GetVideoDetailReqDTO;
import com.dousheng.dto.resp.video.GetVideoDetailListRespDTO;
import com.dousheng.dto.resp.video.GetVideoDetailRespDTO;
import com.dousheng.feed.common.convention.exception.RemoteException;
import com.dousheng.feed.mq.message.InteractionIndicatorMsg;
import com.dousheng.feed.toolkit.FeedScoreCalculator;
import com.dousheng.service.VideoRpcService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import static com.dousheng.feed.common.constant.MqConstant.*;
import static com.dousheng.feed.common.constant.RedisCacheConstant.GLOBAL_HOT_VIDEO_KEY;
import static com.dousheng.feed.common.constant.SuccessBaseRespConstant.SUCCESS_CODE;

@Component
@RequiredArgsConstructor
@RocketMQMessageListener(topic = INTERACTION_INDICATOR_TOPIC, consumerGroup = INTERACTION_INDICATOR_CONSUMER_GROUP)
public class HotVideoScoreUpdateConsumer implements RocketMQListener<MessageExt> {

    private static final Log log = LogFactory.get();

    private final RedisTemplate redisTemplate;
    @DubboReference(timeout = 4000, retries = 3, loadbalance = "roundrobin")
    private VideoRpcService videoRpcService;

    @Override
    public void onMessage(MessageExt messageExt) {
        InteractionIndicatorMsg interactionIndicatorMsg = JSON.parseObject(messageExt.getBody(), InteractionIndicatorMsg.class);
        GetVideoDetailReqDTO reqDTO = GetVideoDetailReqDTO.builder().userId(interactionIndicatorMsg.getUserId()).videoId(interactionIndicatorMsg.getVideoId()).build();
        GetVideoDetailRespDTO respDTO = videoRpcService.getVideoDetail(reqDTO);
        if (!respDTO.getCode().equals(SUCCESS_CODE)) {
            log.error("[HotVideoScoreUpdateConsumer] get video detail failed, req={}, resp={}", reqDTO, respDTO);
            throw new RemoteException(respDTO.getMessage());
        }
        VideoInfoDTO videoInfoDTO = respDTO.getVideoInfo();
        redisTemplate.opsForZSet().add(GLOBAL_HOT_VIDEO_KEY, videoInfoDTO.getId(), FeedScoreCalculator.calculate(videoInfoDTO));
    }
}
