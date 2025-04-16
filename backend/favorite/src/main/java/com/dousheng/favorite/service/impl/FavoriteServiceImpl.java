package com.dousheng.favorite.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dousheng.dto.common.VideoInfoDTO;
import com.dousheng.dto.req.favorite.*;
import com.dousheng.dto.req.video.GetBaseVideoListReqDTO;
import com.dousheng.dto.req.video.GetVideoDetailListReqDTO;
import com.dousheng.dto.resp.favorite.*;
import com.dousheng.dto.resp.video.GetBaseVideoListRespDTO;
import com.dousheng.dto.resp.video.GetVideoDetailListRespDTO;
import com.dousheng.favorite.common.convention.exception.ClientException;
import com.dousheng.favorite.common.convention.exception.RemoteException;
import com.dousheng.favorite.common.convention.exception.ServiceException;
import com.dousheng.favorite.common.enums.FavoriteActionTypeEnum;
import com.dousheng.favorite.dao.entity.FavoriteDO;
import com.dousheng.favorite.dao.mapper.FavoriteMapper;
import com.dousheng.favorite.mq.message.FavoriteActionMsg;
import com.dousheng.favorite.mq.message.InteractionIndicatorMsg;
import com.dousheng.favorite.service.FavoriteService;
import com.dousheng.service.VideoRpcService;
import io.netty.util.internal.ThreadLocalRandom;
import lombok.RequiredArgsConstructor;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static com.dousheng.favorite.common.constant.MqConstant.FAVORITE_ACTION_TOPIC;
import static com.dousheng.favorite.common.constant.MqConstant.INTERACTION_INDICATOR_TOPIC;
import static com.dousheng.favorite.common.constant.RedisCacheConstant.*;
import static com.dousheng.favorite.common.constant.SuccessBaseRespConstant.SUCCESS_CODE;
import static com.dousheng.favorite.common.constant.SuccessBaseRespConstant.SUCCESS_MESSAGE;
import static com.dousheng.favorite.common.enums.FavoriteActionTypeEnum.DISLIKE;
import static com.dousheng.favorite.common.enums.FavoriteActionTypeEnum.LIKE;
import static com.dousheng.favorite.common.enums.FavoriteErrorCodeEnum.*;
import static org.apache.rocketmq.client.producer.SendStatus.SEND_OK;

@Service
@RequiredArgsConstructor
public class FavoriteServiceImpl extends ServiceImpl<FavoriteMapper, FavoriteDO> implements FavoriteService {

    @DubboReference(timeout = 5000, retries = 3, loadbalance = "roundrobin", check = false)
    private VideoRpcService videoRpcService;

    private final RedissonClient redissonClient;
    private final RedisTemplate redisTemplate;
    private final RocketMQTemplate rocketMQTemplate;

    @Override
    public FavoriteActionRespDTO favoriteAction(FavoriteActionReqDTO requestParam) {
        this.checkParam(requestParam);

        FavoriteActionMsg msg = BeanUtil.copyProperties(requestParam, FavoriteActionMsg.class);
        SendResult sendResult = rocketMQTemplate.syncSendOrderly(FAVORITE_ACTION_TOPIC, MessageBuilder.withPayload(msg).build(), msg.getUserId() + ":" + msg.getVideoId());
        if (!sendResult.getSendStatus().equals(SEND_OK)) {
            throw new ServiceException(SEND_MESSAGE_ERROR);
        }

        return FavoriteActionRespDTO.builder().
                code(SUCCESS_CODE).
                message(SUCCESS_MESSAGE).
                build();
    }

    @Override
    public GetFavoriteListRespDTO getFavoriteList(GetFavoriteListReqDTO requestParam) {
        this.checkParam(requestParam);

        LambdaQueryWrapper<FavoriteDO> queryWrapper = Wrappers.lambdaQuery(FavoriteDO.class)
                .eq(FavoriteDO::getUserId, requestParam.getUserId());
        List<FavoriteDO> favoriteDOList = baseMapper.selectList(queryWrapper);

        if (CollectionUtil.isEmpty(favoriteDOList)) {
            return GetFavoriteListRespDTO.builder().
                    code(SUCCESS_CODE).
                    message(SUCCESS_MESSAGE).
                    build();
        }

        List<Long> videoIds = favoriteDOList.stream().map(FavoriteDO::getVideoId).toList();
        GetVideoDetailListRespDTO videoDetailListRespDTO = videoRpcService.getVideoDetailList(
                GetVideoDetailListReqDTO.
                        builder().
                        userId(requestParam.getUserId()).
                        videoIdList(videoIds).
                        build());
        if (!videoDetailListRespDTO.getCode().equals(SUCCESS_CODE)) {
            throw new RemoteException(videoDetailListRespDTO.getMessage());
        }
        if (CollectionUtil.isEmpty(videoDetailListRespDTO.getVideoInfoList())) { // 有可能是作者转私密了
            return GetFavoriteListRespDTO.builder().
                    code(SUCCESS_CODE).
                    message(SUCCESS_MESSAGE).
                    build();
        }
        videoDetailListRespDTO.getVideoInfoList().sort((a, b) -> Long.compare(b.getCreateTime().getTime(), a.getCreateTime().getTime()));
        int totalCount = videoDetailListRespDTO.getVideoInfoList().size();
        int start = (requestParam.getPage() - 1) * requestParam.getPageSize();
        int end = Math.min(start + requestParam.getPageSize(), totalCount);

        return GetFavoriteListRespDTO.builder().
                code(SUCCESS_CODE).
                message(SUCCESS_MESSAGE).
                page(requestParam.getPage()).
                totalPage(totalCount / requestParam.getPageSize() > 0 ? totalCount / requestParam.getPageSize() : 1).
                totalCount(totalCount).
                videoList(videoDetailListRespDTO.getVideoInfoList().subList(start, end)).
                build();
    }

    @Override
    public GetTotalLikeMeCountRespDTO getTotalLikeMeCount(GetTotalLikeMeCountReqDTO requestParam) {
        this.checkParam(requestParam);
        Integer count = this.checkAndSetUserLikedCount(requestParam.getUserId());
        return GetTotalLikeMeCountRespDTO.builder().
                code(SUCCESS_CODE).
                message(SUCCESS_MESSAGE).
                totalLikeMeCount(count).
                build();
    }

    @Override
    public GetVideoLikeCountsRespDTO getVideoLikeCounts(GetVideoLikeCountsReqDTO requestParam) {
        this.checkParam(requestParam);
        Integer count = this.checkAndSetVideoLikedCount(requestParam.getVideoId());
        return GetVideoLikeCountsRespDTO.builder().
                code(SUCCESS_CODE).
                message(SUCCESS_MESSAGE).
                likeCounts(count).
                build();
    }

    @Override
    public IsLikeVideoRespDTO isLikeVideo(IsLikeVideoReqDTO requestParam) {
        this.checkParam(requestParam);

        Double score = redisTemplate.opsForZSet().score(String.format(USER_FAVORITE_KEY, requestParam.getUserId()), requestParam.getVideoId());
        boolean isLike = score != null;
        if (isLike) {
            return IsLikeVideoRespDTO.builder().
                    code(SUCCESS_CODE).
                    message(SUCCESS_MESSAGE).
                    isLike(true).
                    build();
        }

        RLock lock = redissonClient.getLock( String.format(LOCK_USER_FAVORITE_KEY, requestParam.getUserId()));
        lock.lock();
        try {
            score = redisTemplate.opsForZSet().score(String.format(USER_FAVORITE_KEY, requestParam.getUserId()), requestParam.getVideoId());
            isLike = score != null;
            if (isLike) {
                return IsLikeVideoRespDTO.builder().
                        code(SUCCESS_CODE).
                        message(SUCCESS_MESSAGE).
                        isLike(true).
                        build();
            }

            LambdaQueryWrapper<FavoriteDO> queryWrapper = Wrappers.lambdaQuery(FavoriteDO.class).
                    eq(FavoriteDO::getUserId, requestParam.getUserId()).
                    eq(FavoriteDO::getVideoId, requestParam.getVideoId());
            isLike = this.baseMapper.exists(queryWrapper);

            if (isLike) {
                redisTemplate.opsForZSet().add(String.format(USER_FAVORITE_KEY, requestParam.getUserId()), requestParam.getVideoId(), Instant.now().getEpochSecond());
                redisTemplate.expire(String.format(USER_FAVORITE_KEY, requestParam.getUserId()), USER_FAVORITE_BASE_TTL + ThreadLocalRandom.current().nextInt(31), TimeUnit.SECONDS);
                trimZSetIfNecessary(String.format(USER_FAVORITE_KEY, requestParam.getUserId()));
            }
        } finally {
            lock.unlock();
        }

        return IsLikeVideoRespDTO.builder().
                code(SUCCESS_CODE).
                message(SUCCESS_MESSAGE).
                isLike(isLike).
                build();
    }

    public void checkParam(FavoriteActionReqDTO requestParam) {
        if (requestParam == null) {
            throw new ClientException(REQUEST_PARAM_NULL);
        }
        if (requestParam.getUserId() == null || requestParam.getAuthorId() == null) {
            throw new ClientException(USER_ID_NULL);
        }
        if (requestParam.getVideoId() == null) {
            throw new ClientException(VIDEO_ID_NULL);
        }
        if (requestParam.getActionType() == null) {
            throw new ClientException(ACTION_TYPE_NULL);
        }
        if (!FavoriteActionTypeEnum.isValid(requestParam.getActionType())) {
            throw new ClientException(ACTION_TYPE_INVALID);
        }
    }

    public void checkParam(IsLikeVideoReqDTO requestParam) {
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

    public void checkParam(GetFavoriteListReqDTO requestParam) {
        if (requestParam == null) {
            throw new ClientException(REQUEST_PARAM_NULL);
        }
        if (requestParam.getUserId() == null) {
            throw new ClientException(USER_ID_NULL);
        }
        if (requestParam.getPage() == null || requestParam.getPageSize() == null) {
            throw new ClientException(PAGE_PARAM_NULL);
        }
    }

    public void checkParam(GetTotalLikeMeCountReqDTO requestParam) {
        if (requestParam == null) {
            throw new ClientException(REQUEST_PARAM_NULL);
        }
        if (requestParam.getUserId() == null) {
            throw new ClientException(USER_ID_NULL);
        }
    }

    public void checkParam(GetVideoLikeCountsReqDTO requestParam) {
        if (requestParam == null) {
            throw new ClientException(REQUEST_PARAM_NULL);
        }
        if (requestParam.getVideoId() == null) {
            throw new ClientException(VIDEO_ID_NULL);
        }
    }

    public void processBatch(List<FavoriteActionMsg> msgList) {
        if (CollectionUtil.isEmpty(msgList)) {
            return;
        }

        List<FavoriteDO> addActionList = new ArrayList<>();
        List<FavoriteActionMsg> removeActionList = new ArrayList<>();

        for (FavoriteActionMsg msg : msgList) {
            IsLikeVideoRespDTO isLikeVideoRespDTO = this.isLikeVideo(
                    IsLikeVideoReqDTO.builder().
                            userId(msg.getUserId()).
                            videoId(msg.getVideoId()).
                            build());
            Boolean isLike = isLikeVideoRespDTO.getIsLike();

            // 避免重复点赞或重复取消点赞
            if ((msg.getActionType().equals(LIKE.type) && isLike) || (msg.getActionType().equals(DISLIKE.type) && !isLike)) {
                continue;
            }

            if (!isLike) {
                FavoriteDO favoriteDO = FavoriteDO.builder().
                        id(IdWorker.getId()).
                        userId(msg.getUserId()).
                        videoId(msg.getVideoId()).
                        build();
                addActionList.add(favoriteDO);
            } else {
                removeActionList.add(msg);
            }

            this.addFavoriteActionToRedis(msg.getUserId(), msg.getAuthorId(), msg.getVideoId(), msg.getActionType());

            rocketMQTemplate.syncSend(
                    INTERACTION_INDICATOR_TOPIC,
                    InteractionIndicatorMsg.builder().
                            source("Favortie-Service").
                            actionType(LIKE.type).
                            userId(msg.getUserId()).
                            videoId(msg.getVideoId()).
                            build());
        }

        // 批量新增
        if (CollectionUtil.isNotEmpty(addActionList)) {
            baseMapper.insertBatchSomeColumn(addActionList);
        }
        // 批量删除
        if (CollectionUtil.isNotEmpty(removeActionList)) {
            LambdaQueryWrapper<FavoriteDO> removeWrapper = Wrappers.lambdaQuery();
            removeWrapper.and(w -> {
                for (FavoriteActionMsg msg : removeActionList) {
                    w.or()
                            .eq(FavoriteDO::getUserId, msg.getUserId())
                            .eq(FavoriteDO::getVideoId, msg.getVideoId());
                }
            });
            this.remove(removeWrapper);
        }
    }

    public void trimZSetIfNecessary(String key) {
        int size = Objects.requireNonNull(redisTemplate.opsForZSet().size(key)).intValue();
        if (size > MAX_CACHE_SIZE) {
            redisTemplate.opsForZSet().removeRange(key, 0, size - MAX_CACHE_SIZE - 1);
        }
    }

    public void addFavoriteActionToRedis(Long userId, Long authorId, Long videoId, Integer actionType) {
        this.checkAndSetUserLikedCount(userId);
        this.checkAndSetVideoLikedCount(videoId);

        String key1 = String.format(USER_FAVORITE_KEY, userId);
        String key2 = String.format(USER_LIKED_COUNT_KEY, authorId);
        String key3 = String.format(VIDEO_LIKED_COUNT_KEY, videoId);

        // 调用之前已经做了幂等判断
        if (actionType.equals(LIKE.type)) {
            redisTemplate.executePipelined(new SessionCallback<Object>() {
                @Override
                public Object execute(RedisOperations operations) throws DataAccessException {
                    operations.opsForZSet().add(key1, videoId, Instant.now().getEpochSecond());
                    operations.opsForValue().increment(key2, 1);
                    operations.opsForValue().increment(key3, 1);
                    return null;
                }
            });
        } else {
            redisTemplate.executePipelined(new SessionCallback<Object>() {
                @Override
                public Object execute(RedisOperations operations) throws DataAccessException {
                    operations.opsForZSet().remove(key1, videoId);
                    operations.opsForValue().increment(key2, -1);
                    operations.opsForValue().increment(key3, -1);
                    return null;
                }
            });
        }

    }

    public Integer checkAndSetUserLikedCount(Long userId) {
        Integer count = (Integer) redisTemplate.opsForValue().get(String.format(USER_LIKED_COUNT_KEY, userId));
        if (count != null) {
            return count;
        }

        RLock lock = redissonClient.getLock( String.format(LOCK_USER_LIKED_COUNT_KEY, userId));
        lock.lock();
        try {
            count = (Integer) redisTemplate.opsForValue().get(String.format(USER_LIKED_COUNT_KEY, userId));
            if (count != null) {
                return count;
            }

            GetBaseVideoListRespDTO respDTO = videoRpcService.getBaseVideoList(
                    GetBaseVideoListReqDTO.builder().
                            userId(userId).
                            build());
            if (!respDTO.getCode().equals(SUCCESS_CODE)) {
                throw new RemoteException(respDTO.getMessage());
            }
            if (CollectionUtil.isEmpty(respDTO.getVideoList())) {
                return 0;
            }
            List<Long> videoIds = respDTO.getVideoList()
                    .stream()
                    .map(VideoInfoDTO::getId)
                    .collect(Collectors.toList());
            LambdaQueryWrapper<FavoriteDO> queryWrapper = Wrappers.lambdaQuery(FavoriteDO.class).
                    in(FavoriteDO::getVideoId, videoIds);
            count = baseMapper.selectCount(queryWrapper).intValue();

            redisTemplate.opsForValue().set(String.format(USER_LIKED_COUNT_KEY, userId), count, USER_LIKED_COUNT_BASE_TTL + ThreadLocalRandom.current().nextInt(31), TimeUnit.SECONDS);
        } finally {
            lock.unlock();
        }

        return count;
    }

    public Integer checkAndSetVideoLikedCount(Long videoId) {
        Integer count = (Integer) redisTemplate.opsForValue().get(String.format(VIDEO_LIKED_COUNT_KEY, videoId));
        if (count != null) {
            return count;
        }

        RLock lock = redissonClient.getLock( String.format(LOCK_VIDEO_LIKED_COUNT_KEY, videoId));
        lock.lock();
        try {
            count = (Integer) redisTemplate.opsForValue().get(String.format(VIDEO_LIKED_COUNT_KEY, videoId));
            if (count != null) {
                return count;
            }

            LambdaQueryWrapper<FavoriteDO> queryWrapper = Wrappers.lambdaQuery(FavoriteDO.class).
                    eq(FavoriteDO::getVideoId, videoId);
            count = baseMapper.selectCount(queryWrapper).intValue();

            redisTemplate.opsForValue().set(String.format(VIDEO_LIKED_COUNT_KEY, videoId), count, VIDEO_LIKED_COUNT_BASE_TTL + ThreadLocalRandom.current().nextInt(31), TimeUnit.SECONDS);
        } finally {
            lock.unlock();
        }

        return count;
    }


}
