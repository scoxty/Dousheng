package com.dousheng.favorite.mq.consumer;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dousheng.favorite.common.convention.exception.ClientException;
import com.dousheng.favorite.common.convention.exception.ServiceException;
import com.dousheng.favorite.common.enums.FavoriteActionTypeEnum;
import com.dousheng.favorite.dao.entity.FavoriteDO;
import com.dousheng.favorite.dao.mapper.FavoriteMapper;
import com.dousheng.favorite.mq.message.OperateDBMsg;
import lombok.RequiredArgsConstructor;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;

import static com.dousheng.favorite.common.constant.MqConstant.*;
import static com.dousheng.favorite.common.enums.FavoriteActionTypeEnum.LIKE;
import static com.dousheng.favorite.common.enums.FavoriteErrorCodeEnum.MQ_DEL_FAVORITE_ERROR;
import static com.dousheng.favorite.common.enums.FavoriteErrorCodeEnum.MQ_SAVE_FAVORITE_ERROR;

@Component
@RequiredArgsConstructor
@RocketMQMessageListener(topic = FAVORITE_OPERATE_DB_TOPIC, consumerGroup = FAVORITE_OPERATE_DB_CONSUMER_GROUP, consumeMode = ConsumeMode.ORDERLY)
public class OperateDBConsumer implements RocketMQListener<MessageExt> {

    private static final Log log = LogFactory.get();

    private final FavoriteMapper favoriteMapper;

    @Override
    public void onMessage(MessageExt messageExt) {
        OperateDBMsg operateDBMsg = JSON.parseObject(messageExt.getBody(), OperateDBMsg.class);

        if (operateDBMsg == null) {
            log.warn("[OperateDBConsumer]: receive null message, fast return");
            return;
        }

        this.checkParam(operateDBMsg);

        // 上游已经做了幂等判断
        if (operateDBMsg.getActionType().equals(LIKE.type)) {
            FavoriteDO favoriteDO = FavoriteDO.builder().
                    id(IdWorker.getId()).
                    userId(operateDBMsg.getUserId()).
                    videoId(operateDBMsg.getVideoId()).
                    build();
            try {
                favoriteMapper.insert(favoriteDO);
            } catch (DuplicateKeyException e) {
                log.warn("[OperateDBConsumer][insertDB]: duplicate key exception, fast return");
            } catch (Exception e) {
                log.error("[OperateDBConsumer][insertDB]: insert db error={}, favoriteDO={}", e, favoriteDO);
                throw new ServiceException(MQ_SAVE_FAVORITE_ERROR);
            }
            
            log.info("[OperateDBConsumer][insertDB]: insert db success, favoriteDO={}", favoriteDO);

        } else {

            try {
                LambdaQueryWrapper<FavoriteDO> lambdaQueryWrapper = Wrappers.lambdaQuery(FavoriteDO.class)
                        .eq(FavoriteDO::getUserId, operateDBMsg.getUserId())
                        .eq(FavoriteDO::getVideoId, operateDBMsg.getVideoId());
                favoriteMapper.delete(lambdaQueryWrapper);
            } catch (Exception e) {
                log.error("[OperateDBConsumer][deleteDB]: delete db error={}, operateDBMsg={}", e, operateDBMsg);
                throw new ServiceException(MQ_DEL_FAVORITE_ERROR);
            }
            
            log.info("[OperateDBConsumer][deleteDB]: delete db success, operateDBMsg={}", operateDBMsg);
        }
    }

    public void checkParam(OperateDBMsg requestParam) {
        if (requestParam.getUserId() == null) {
            log.error("[OperateDBConsumer][checkParam]: userId is null, requestParam={}", requestParam);
            throw new ServiceException("userId is null");
        }
        if (requestParam.getVideoId() == null) {
            log.error("[OperateDBConsumer][checkParam]: videoId is null, requestParam={}", requestParam);
            throw new ServiceException("videoId is null");
        }
        if (requestParam.getActionType() == null) {
            log.error("[OperateDBConsumer][checkParam]: actionType is null, requestParam={}", requestParam);
            throw new ServiceException("actionType is null");
        }
        if (!FavoriteActionTypeEnum.isValid(requestParam.getActionType())) {
            log.error("[OperateDBConsumer][checkParam]: actionType is invalid, requestParam={}", requestParam);
            throw new ServiceException("actionType is invalid");
        }
    }
}
