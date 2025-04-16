package com.dousheng.favorite.mq.consumer;

import com.alibaba.fastjson2.JSON;
import com.dousheng.favorite.mq.consumer.aggregator.FavoriteActionAggregator;
import com.dousheng.favorite.mq.message.FavoriteActionMsg;
import com.dousheng.favorite.service.FavoriteService;
import lombok.RequiredArgsConstructor;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

import static com.dousheng.favorite.common.constant.MqConstant.FAVORITE_ACTION_CONSUMER_GROUP;
import static com.dousheng.favorite.common.constant.MqConstant.FAVORITE_ACTION_TOPIC;

@Component
@RequiredArgsConstructor
@RocketMQMessageListener(topic = FAVORITE_ACTION_TOPIC, consumerGroup = FAVORITE_ACTION_CONSUMER_GROUP, consumeMode = ConsumeMode.ORDERLY)
public class FavoriteActionConsumer implements RocketMQListener<MessageExt> {

    private final FavoriteActionAggregator favoriteActionAggregator;

    @Override
    public void onMessage(MessageExt messageExt) {
        FavoriteActionMsg msg = JSON.parseObject(new String(messageExt.getBody()), FavoriteActionMsg.class);
        favoriteActionAggregator.add(msg);
    }
}
