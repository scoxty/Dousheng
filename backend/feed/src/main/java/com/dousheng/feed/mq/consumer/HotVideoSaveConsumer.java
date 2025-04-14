package com.dousheng.feed.mq.consumer;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.alibaba.fastjson2.JSON;
import com.dousheng.dto.common.VideoInfoDTO;
import com.dousheng.feed.common.convention.exception.ClientException;
import com.dousheng.feed.toolkit.FeedScoreCalculator;
import lombok.RequiredArgsConstructor;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import static com.dousheng.feed.common.constant.MqConstant.HOT_VIDEO_CONSUMER_GROUP;
import static com.dousheng.feed.common.constant.MqConstant.HOT_VIDEO_TOPIC;
import static com.dousheng.feed.common.constant.RedisCacheConstant.GLOBAL_HOT_VIDEO_KEY;
import static com.dousheng.feed.common.enums.FeedErrorCodeEnum.*;

@Component
@RequiredArgsConstructor
@RocketMQMessageListener(topic = HOT_VIDEO_TOPIC, consumerGroup = HOT_VIDEO_CONSUMER_GROUP)
public class HotVideoSaveConsumer implements RocketMQListener<MessageExt> {

    private static final Log log = LogFactory.get();

    private final RedisTemplate redisTemplate;

    @Override
    public void onMessage(MessageExt msg) {
        VideoInfoDTO video = JSON.parseObject(msg.getBody(), VideoInfoDTO.class);
        if (video == null) {
            log.error("[HotVideoSaveConsumer] video info is null");
            throw new ClientException(VIDEO_INFO_IS_NULL);
        }
        if (video.getId() == null) {
            log.error("[HotVideoSaveConsumer] video id is null");
            throw new ClientException(VIDEO_ID_IS_NULL);
        }
        if (video.getLikeCounts() == null) {
            log.error("[HotVideoSaveConsumer] video likeCounts is null");
            throw new ClientException(LIKE_COUNTS_IS_NULL);
        }
        if (video.getCommentCounts() == null) {
            log.error("[HotVideoSaveConsumer] video commentCounts is null");
            throw new ClientException(COMMENT_COUNTS_IS_NULL);
        }
        if (video.getCreateTime() == null) {
            log.error("[HotVideoSaveConsumer] video createTime is null");
            throw new ClientException(CREATE_TIME_IS_NULL);
        }
        redisTemplate.opsForZSet().add(GLOBAL_HOT_VIDEO_KEY, video.getId(), FeedScoreCalculator.calculate(video));
    }
}
