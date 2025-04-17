package com.dousheng.relation.mq.consumer;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dousheng.relation.common.convention.exception.ClientException;
import com.dousheng.relation.common.convention.exception.ServiceException;
import com.dousheng.relation.common.enums.RelationActionTypeEnum;
import com.dousheng.relation.dao.entity.RelationDO;
import com.dousheng.relation.dao.mapper.RelationMapper;
import com.dousheng.relation.mq.message.OperateDBMsg;
import lombok.RequiredArgsConstructor;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;

import static com.dousheng.relation.common.constant.MqConstant.RELATION_OPERATE_DB_CONSUMER_GROUP;
import static com.dousheng.relation.common.constant.MqConstant.RELATION_OPERATE_DB_TOPIC;
import static com.dousheng.relation.common.enums.RelationActionTypeEnum.FOLLOW;
import static com.dousheng.relation.common.enums.RelationErrorCodeEnum.*;

@Component
@RequiredArgsConstructor
@RocketMQMessageListener(topic = RELATION_OPERATE_DB_TOPIC, consumerGroup = RELATION_OPERATE_DB_CONSUMER_GROUP, consumeMode = ConsumeMode.ORDERLY)
public class OperateDBConsumer implements RocketMQListener<MessageExt> {

    private static final Log log = LogFactory.get();

    private final RelationMapper relationMapper;

    @Override
    public void onMessage(MessageExt messageExt) {
        OperateDBMsg operateDBMsg = JSON.parseObject(messageExt.getBody(), OperateDBMsg.class);

        if (operateDBMsg == null) {
            log.warn("[OperateDBConsumer]: receive null message, fast return");
            return;
        }

        this.checkParam(operateDBMsg);

        // 上游已经做了幂等判断
        if (operateDBMsg.getActionType().equals(FOLLOW.type)) {
            RelationDO relationDO = RelationDO.builder()
                    .id(IdWorker.getId())
                    .userId(operateDBMsg.getToUserId())
                    .fanId(operateDBMsg.getFromUserId())
                    .build();
            try {
                relationMapper.insert(relationDO);
            } catch (DuplicateKeyException e) {
                log.warn("[OperateDBConsumer][insertDB]: duplicate key exception, fast return");
            } catch (Exception e) {
                log.error("[OperateDBConsumer][insertDB]: insert relation error={}, relationDO={}", e, relationDO);
                throw new ServiceException(e.getMessage(), MQ_SAVE_RELATION_ERROR);
            }

            log.info("[OperateDBConsumer][insertDB]: insert relation success, relationDO={}", relationDO);

        } else {
            try {
                LambdaQueryWrapper<RelationDO> deleteWrapper = Wrappers.lambdaQuery(RelationDO.class)
                        .eq(RelationDO::getUserId, operateDBMsg.getToUserId())
                        .eq(RelationDO::getFanId, operateDBMsg.getFromUserId());
                relationMapper.delete(deleteWrapper);
            } catch (Exception e) {
                log.error("[OperateDBConsumer][deleteDB]: delete relation error={}, operateDBMsg: {}", e, operateDBMsg);
                throw new ServiceException(e.getMessage(), MQ_DEL_RELATION_ERROR);
            }

            log.info("[OperateDBConsumer][deleteDB]: delete relation success, operateDBMsg: {}", operateDBMsg);
        }
    }

    public void checkParam(OperateDBMsg requestParam) {
        if (requestParam.getFromUserId() == null) {
            log.error("[OperateDBConsumer][checkParam]: fromUserId is null, requestParam: {}", requestParam);
            throw new ServiceException("fromUserId is null");
        }
        if (requestParam.getToUserId() == null) {
            log.error("[OperateDBConsumer][checkParam]: toUserId is null, requestParam: {}", requestParam);
            throw new ServiceException("toUserId is null");
        }
        if (requestParam.getActionType() == null) {
            log.error("[OperateDBConsumer][checkParam]: actionType is null, requestParam: {}", requestParam);
            throw new ServiceException("actionType is null");
        }
        if (!RelationActionTypeEnum.isValid(requestParam.getActionType())) {
            log.error("[OperateDBConsumer][checkParam]: actionType is invalid, requestParam: {}", requestParam);
            throw new ServiceException("actionType is invalid");
        }
    }
}
