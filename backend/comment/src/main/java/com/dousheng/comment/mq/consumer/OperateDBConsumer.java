package com.dousheng.comment.mq.consumer;

import cn.hutool.core.util.StrUtil;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dousheng.comment.common.convention.exception.ServiceException;
import com.dousheng.comment.common.enums.CommentActionTypeEnum;
import com.dousheng.comment.dao.entity.CommentDO;
import com.dousheng.comment.dao.mapper.CommentMapper;
import com.dousheng.comment.mq.message.OperateDBMsg;
import com.dousheng.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

import static com.dousheng.comment.common.constant.MqConstant.COMMENT_OPERATE_DB_CONSUMER_GROUP;
import static com.dousheng.comment.common.constant.MqConstant.COMMENT_OPERATE_DB_TOPIC;
import static com.dousheng.comment.common.enums.CommentActionTypeEnum.COMMENT;
import static com.dousheng.comment.common.enums.CommentErrorCodeEnum.MQ_DEL_COMMENT_ERROR;
import static com.dousheng.comment.common.enums.CommentErrorCodeEnum.MQ_SAVE_COMMENT_ERROR;

@Component
@RequiredArgsConstructor
@RocketMQMessageListener(topic = COMMENT_OPERATE_DB_TOPIC, consumerGroup = COMMENT_OPERATE_DB_CONSUMER_GROUP, consumeMode = ConsumeMode.ORDERLY)
public class OperateDBConsumer implements RocketMQListener<MessageExt> {

    private static final Log log = LogFactory.get();
    private final CommentMapper commentMapper;

    @Override
    public void onMessage(MessageExt messageExt) {
        OperateDBMsg operateDBMsg = JSON.parseObject(messageExt.getBody(), OperateDBMsg.class);

        this.checkParam(operateDBMsg);

        if (operateDBMsg.getActionType().equals(COMMENT.type)) {
            CommentDO commentDO = CommentDO.builder()
                    .id(operateDBMsg.getId())
                    .fatherCommentId(operateDBMsg.getFatherCommentId())
                    .userId(operateDBMsg.getUserId())
                    .authorId(operateDBMsg.getAuthorId())
                    .videoId(operateDBMsg.getVideoId())
                    .content(operateDBMsg.getContent())
                    .build();

            try {
                commentMapper.insert(commentDO);
            } catch (Exception e) {
                log.error("[OperateDBConsumer][insertDB] insert comment failed, commentDO={}, err={}", commentDO, e);
                throw new ServiceException(e.getMessage(), MQ_SAVE_COMMENT_ERROR);
            }
            log.info("[OperateDBConsumer][insertDB] insert comment success, commentDO={}", commentDO);
        } else {

            try {
                LambdaQueryWrapper<CommentDO> deleteWrapper = Wrappers.lambdaQuery(CommentDO.class)
                        .eq(CommentDO::getId, operateDBMsg.getId());
                commentMapper.delete(deleteWrapper);
            } catch (Exception e) {
                log.error("[OperateDBConsumer][deleteDB] delete comment failed, operateDBMsg={}, err={}", operateDBMsg, e);
                throw new ServiceException(e.getMessage(), MQ_DEL_COMMENT_ERROR);
            }
            log.info("[OperateDBConsumer][deleteDB] delete comment success, operateDBMsg={}", operateDBMsg);
        }

    }

    public void checkParam(OperateDBMsg requestParam) {
        if (requestParam.getActionType() == null) {
            log.error("[OperateDBConsumer][checkParam] actionType is null, requestParam={}", requestParam);
            throw new ServiceException("actionType is null");
        }

        if (!CommentActionTypeEnum.isValid(requestParam.getActionType())) {
            log.error("[OperateDBConsumer][checkParam] actionType is invalid, requestParam={}", requestParam);
            throw new ServiceException("actionType is invalid");
        }

        if (requestParam.getUserId() == null) {
            log.error("[OperateDBConsumer][checkParam] userId is null, requestParam={}", requestParam);
            throw new ServiceException("userId is null");
        }

        if (requestParam.getVideoId() == null) {
            log.error("[OperateDBConsumer][checkParam] videoId is null, requestParam={}", requestParam);
            throw new ServiceException("videoId is null");
        }


        if (requestParam.getActionType().equals(COMMENT.type)) {
            if (requestParam.getAuthorId() == null) {
                log.error("[OperateDBConsumer][checkParam] authorId is null, requestParam={}", requestParam);
                throw new ServiceException("authorId is null");
            }

            if (StrUtil.isBlank(requestParam.getContent())) {
                log.error("[OperateDBConsumer][checkParam] content is empty, requestParam={}", requestParam);
                throw new ServiceException("content is empty");
            }
        } else {
            if (requestParam.getId() == null) {
                log.error("[OperateDBConsumer][checkParam] commentId is null, requestParam={}", requestParam);
                throw new ServiceException("commentId is null");
            }

        }

    }
}
