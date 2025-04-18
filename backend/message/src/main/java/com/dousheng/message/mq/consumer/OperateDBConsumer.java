package com.dousheng.message.mq.consumer;

import cn.hutool.core.util.StrUtil;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.dousheng.dto.req.message.GetMessageListReqDTO;
import com.dousheng.message.common.convention.exception.ServiceException;
import com.dousheng.message.dao.entity.MessageDO;
import com.dousheng.message.dao.mapper.MessageMapper;
import com.dousheng.message.mq.message.OperateDBMsg;
import lombok.RequiredArgsConstructor;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

import static com.dousheng.message.common.constant.MqConstant.MESSAGE_OPERATE_DB_CONSUMER_GROUP;
import static com.dousheng.message.common.constant.MqConstant.MESSAGE_OPERATE_DB_TOPIC;
import static com.dousheng.message.common.enums.MessageErrorCodeEnum.MQ_SAVE_MESSAGE_ERROR;

@Component
@RequiredArgsConstructor
@RocketMQMessageListener(topic = MESSAGE_OPERATE_DB_TOPIC, consumerGroup = MESSAGE_OPERATE_DB_CONSUMER_GROUP)
public class OperateDBConsumer implements RocketMQListener<MessageExt> {

    private static final Log log = LogFactory.get();
    private final MessageMapper messageMapper;

    @Override
    public void onMessage(MessageExt messageExt) {
        OperateDBMsg operateDBMsg = JSON.parseObject(messageExt.getBody(), OperateDBMsg.class);
        if (operateDBMsg == null) {
            log.warn("[OperateDBConsumer]: receive null message, fast return");
            return;
        }

        this.checkParam(operateDBMsg);

        MessageDO messageDO = MessageDO.builder()
                .id(IdWorker.getId())
                .senderId(operateDBMsg.getSenderId())
                .receiverId(operateDBMsg.getReceiverId())
                .content(operateDBMsg.getContent())
                .build();

        try {
            messageMapper.insert(messageDO);
        } catch (Exception e) {
            log.error("[OperateDBConsumer][insertDB]: insert message error, err={}, messageDO={}", e, messageDO);
            throw new ServiceException(MQ_SAVE_MESSAGE_ERROR);
        }
        log.info("[OperateDBConsumer][insertDB]: insert message success, messageDO={}", messageDO);
    }

    public void checkParam(OperateDBMsg requestParam) {
        if (requestParam.getSenderId() == null) {
            log.error("[OperateDBConsumer][checkParam] senderId is null");
            throw new ServiceException("senderId is null");
        }
        if (requestParam.getReceiverId() == null) {
            log.error("[OperateDBConsumer][checkParam] receiverId is null");
            throw new ServiceException("receiverId is null");
        }
        if (StrUtil.isEmpty(requestParam.getContent())) {
            log.error("[OperateDBConsumer][checkParam] content is empty");
            throw new ServiceException("content is empty");
        }
    }
}
