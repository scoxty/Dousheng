package com.dousheng.relation.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dousheng.dto.common.UserInfoDTO;
import com.dousheng.dto.req.relation.*;
import com.dousheng.dto.req.user.GetBaseUserInfoListReqDTO;
import com.dousheng.dto.req.user.IsExistReqDTO;
import com.dousheng.dto.resp.relation.*;
import com.dousheng.dto.resp.user.GetBaseUserInfoListRespDTO;
import com.dousheng.dto.resp.user.IsExistRespDTO;
import com.dousheng.relation.common.convention.exception.ClientException;
import com.dousheng.relation.common.convention.exception.RemoteException;
import com.dousheng.relation.common.convention.exception.ServiceException;
import com.dousheng.relation.common.enums.RelationActionTypeEnum;
import com.dousheng.relation.dao.entity.RelationDO;
import com.dousheng.relation.dao.mapper.RelationMapper;
import com.dousheng.relation.mq.message.OperateDBMsg;
import com.dousheng.relation.service.RelationService;
import com.dousheng.service.UserRpcService;
import io.netty.util.internal.ThreadLocalRandom;
import lombok.RequiredArgsConstructor;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static com.dousheng.relation.common.constant.MqConstant.RELATION_OPERATE_DB_TOPIC;
import static com.dousheng.relation.common.constant.RedisCacheConstant.*;
import static com.dousheng.relation.common.constant.SuccessBaseRespConstant.SUCCESS_CODE;
import static com.dousheng.relation.common.constant.SuccessBaseRespConstant.SUCCESS_MESSAGE;
import static com.dousheng.relation.common.enums.RelationActionTypeEnum.FOLLOW;
import static com.dousheng.relation.common.enums.RelationErrorCodeEnum.*;

@Service
@RequiredArgsConstructor
public class RelationServiceImpl extends ServiceImpl<RelationMapper, RelationDO> implements RelationService {

    @DubboReference(timeout = 4000, retries = 3, loadbalance = "roundrobin", check = false)
    private UserRpcService userRpcService;

    private final RBloomFilter<String> relationCachePenetrationBloomFilter; // key: 关注者Id:被关注者Id。用于快速判断关注关系
    private final RBloomFilter<Long> followCachePenetrationBloomFilter; // key: 被关注者Id。用于快速判断是否有被人关注过
    private final RBloomFilter<Long> fanCachePenetrationBloomFilter; // key: 关注者Id。用于快速判断是否关注过其他人
    private final RedisTemplate redisTemplate;
    private final RedissonClient redissonClient;
    private final RocketMQTemplate rocketMQTemplate;

    private final ExecutorService executorService = new ThreadPoolExecutor(
            Runtime.getRuntime().availableProcessors() << 1,
            Runtime.getRuntime().availableProcessors() << 2,
            60L,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(200),
            new ThreadPoolExecutor.CallerRunsPolicy());

    @Override
    public FollowActionRespDTO followAction(FollowActionReqDTO requestParam) {
        this.checkParam(requestParam);

        IsExistRespDTO isExistRespDTO = userRpcService.isExist(
                IsExistReqDTO.builder().
                        userIdList(List.of(requestParam.getFromUserId(), requestParam.getToUserId())).
                        build()
        );
        if (!isExistRespDTO.getCode().equals(SUCCESS_CODE)) {
            throw new RemoteException(isExistRespDTO.getMessage());
        }
        if (!isExistRespDTO.getIsExist()) {
            throw new ServiceException(USER_NOT_EXIST);
        }

        if (requestParam.getActionType().equals(FOLLOW.type)) {
            boolean isFollow = Boolean.TRUE.equals(redisTemplate.opsForSet().isMember(String.format(USER_FOLLOW_KEY, requestParam.getFromUserId()), requestParam.getToUserId()));
            if (isFollow) {
                throw new ServiceException(REPEAT_FOLLOW);
            }

            RLock lock = redissonClient.getLock(String.format(LOCK_FOLLOW_ACTION_KEY, requestParam.getFromUserId()));
            lock.lock();
            try {
                checkAndSetRelationKey(requestParam.getFromUserId(), FOLLOW_LIST);
                checkAndSetRelationKey(requestParam.getToUserId(), FAN_LIST);

                // double check
                isFollow = Boolean.TRUE.equals(redisTemplate.opsForSet().isMember(String.format(USER_FOLLOW_KEY, requestParam.getFromUserId()), requestParam.getToUserId()));
                if (isFollow) {
                    throw new ServiceException(REPEAT_FOLLOW);
                }

                // 关注 (redis)
                this.actionFollow(requestParam.getFromUserId(), requestParam.getToUserId());

                // 通过MQ重试保证写入MySQL成功
                executorService.execute(()->{
                    rocketMQTemplate.syncSendOrderly(
                            RELATION_OPERATE_DB_TOPIC,
                            OperateDBMsg.builder().
                                    fromUserId(requestParam.getFromUserId()).
                                    toUserId(requestParam.getToUserId()).
                                    actionType(requestParam.getActionType()).
                                    build(),
                            requestParam.getFromUserId() + ":" + requestParam.getToUserId()
                    );
                });

                // 添加布隆过滤器
                executorService.execute(()->{
                    relationCachePenetrationBloomFilter.add(requestParam.getFromUserId() + ":" + requestParam.getToUserId());
                    followCachePenetrationBloomFilter.add(requestParam.getToUserId());
                    fanCachePenetrationBloomFilter.add(requestParam.getFromUserId());
                });
            } finally {
                lock.unlock();
            }

        } else {

            RLock lock = redissonClient.getLock(String.format(LOCK_FOLLOW_ACTION_KEY, requestParam.getFromUserId()));
            lock.lock();
            try {
                checkAndSetRelationKey(requestParam.getFromUserId(), FOLLOW_LIST);
                checkAndSetRelationKey(requestParam.getToUserId(), FAN_LIST);

                boolean isFollow = Boolean.TRUE.equals(redisTemplate.opsForSet().isMember(String.format(USER_FOLLOW_KEY, requestParam.getFromUserId()), requestParam.getToUserId()));
                if (!isFollow) {
                    throw new ServiceException(REPEAT_CANCEL_FOLLOW);
                }

                // 取消关注 (redis)
                this.actionCancelFollow(requestParam.getFromUserId(), requestParam.getToUserId());

                // 通过MQ重试保证MySQL删除成功
                executorService.execute(()->{
                    rocketMQTemplate.syncSendOrderly(
                            RELATION_OPERATE_DB_TOPIC,
                            OperateDBMsg.builder().
                                    fromUserId(requestParam.getFromUserId()).
                                    toUserId(requestParam.getToUserId()).
                                    actionType(requestParam.getActionType()).
                                    build(),
                            requestParam.getFromUserId() + ":" + requestParam.getToUserId()
                    );
                });

            } finally {
                lock.unlock();
            }
        }

        return FollowActionRespDTO.builder().
                code(SUCCESS_CODE).
                message(SUCCESS_MESSAGE).
                build();
    }

    @Override
    public IsFollowRespDTO isFollow(IsFollowReqDTO requestParam) {
        this.checkParam(requestParam);

        if (!relationCachePenetrationBloomFilter.contains(requestParam.getFromUserId() + ":" + requestParam.getToUserId())) {
            return IsFollowRespDTO.builder().
                    code(SUCCESS_CODE).
                    message(SUCCESS_MESSAGE).
                    isFollow(false).
                    build();
        }

        this.checkAndSetRelationKey(requestParam.getFromUserId(), FOLLOW_LIST);
        this.checkAndSetRelationKey(requestParam.getToUserId(), FAN_LIST);

        boolean isFollow = Boolean.TRUE.equals(redisTemplate.opsForSet().isMember(String.format(USER_FOLLOW_KEY, requestParam.getFromUserId()), requestParam.getToUserId()));

        return IsFollowRespDTO.builder().
                code(SUCCESS_CODE).
                message(SUCCESS_MESSAGE).
                isFollow(isFollow).
                build();
    }

    @Override
    public GetFollowListRespDTO getFollowList(GetFollowListReqDTO requestParam) {
        this.checkParam(requestParam);

        // 自己快速判断自己是否当过粉丝
        if (!fanCachePenetrationBloomFilter.contains(requestParam.getUserId())) {
            return GetFollowListRespDTO.builder().
                    code(SUCCESS_CODE).
                    message(SUCCESS_MESSAGE).
                    followList(List.of())
                    .build();
        }

        this.checkAndSetRelationKey(requestParam.getUserId(), FOLLOW_LIST);

        Set<Object> rawSet =  redisTemplate.opsForSet().members(String.format(USER_FOLLOW_KEY, requestParam.getUserId()));
        if (CollectionUtil.isEmpty(rawSet)) {
            GetFollowListRespDTO.builder().
                    code(SUCCESS_CODE).
                    message(SUCCESS_MESSAGE).
                    followList(List.of())
                    .build();
        }
        List<Long> followIds = Objects.requireNonNull(rawSet).stream()
                .map(o -> Long.valueOf(o.toString()))
                .toList();

        GetBaseUserInfoListRespDTO getBaseUserInfoListRespDTO = userRpcService.getBaseUserInfoList(
                GetBaseUserInfoListReqDTO.builder().
                        userIds(followIds).
                        build()
        );
        if (!getBaseUserInfoListRespDTO.getCode().equals(SUCCESS_CODE)) {
            throw new RemoteException(getBaseUserInfoListRespDTO.getMessage());
        }

        List<UserInfoDTO> userInfoDTOS = getBaseUserInfoListRespDTO.getUserInfos();
        userInfoDTOS.sort((a, b) -> StrUtil.compare(a.getName(), b.getName(), true));
        int totalCount = userInfoDTOS.size();
        int start = (requestParam.getPage() - 1) * requestParam.getPageSize();
        int end = Math.min(start + requestParam.getPageSize(), totalCount);
        List<UserInfoDTO> pageUserInfos = userInfoDTOS.subList(start, end);
        for (UserInfoDTO userInfoDTO : pageUserInfos) {
            userInfoDTO.setIsFollow(true);
        }

        return GetFollowListRespDTO.builder().
                code(SUCCESS_CODE).
                message(SUCCESS_MESSAGE).
                page(requestParam.getPage()).
                totalPage(totalCount / requestParam.getPageSize() > 0 ? totalCount / requestParam.getPageSize() : 1).
                totalCount(totalCount).
                followList(userInfoDTOS.subList(start, end)).
                build();
    }

    @Override
    public GetFollowsCountRespDTO getFollowsCount(GetFollowsCountReqDTO requestParam) {
        this.checkParam(requestParam);

        // 自己快速判断自己是否当过粉丝
        if (!fanCachePenetrationBloomFilter.contains(requestParam.getUserId())) {
            return GetFollowsCountRespDTO.builder().
                    code(SUCCESS_CODE).
                    message(SUCCESS_MESSAGE).
                    followsCount(0).
                    build();
        }

        this.checkAndSetRelationKey(requestParam.getUserId(), FOLLOW_LIST);

        Long followsCount = redisTemplate.opsForSet().size(String.format(USER_FOLLOW_KEY, requestParam.getUserId()));

        return GetFollowsCountRespDTO.builder().
                code(SUCCESS_CODE).
                message(SUCCESS_MESSAGE).
                followsCount(followsCount == null ? 0 : followsCount.intValue()).
                build();
    }

    @Override
    public GetFanListRespDTO getFanList(GetFanListReqDTO requestParam) {
        this.checkParam(requestParam);

        // 自己快速判断自己是否有被关注过
        if (!followCachePenetrationBloomFilter.contains(requestParam.getUserId())) {
            return GetFanListRespDTO.builder().
                    code(SUCCESS_CODE).
                    message(SUCCESS_MESSAGE).
                    fanList(List.of()).
                    build();
        }

        this.checkAndSetRelationKey(requestParam.getUserId(), FAN_LIST);

        Set<Object> rawSet = redisTemplate.opsForSet().members(String.format(USER_FAN_KEY, requestParam.getUserId()));
        if (CollectionUtil.isEmpty(rawSet)) {
            GetFanListRespDTO.builder().
                    code(SUCCESS_CODE).
                    message(SUCCESS_MESSAGE).
                    fanList(List.of())
                    .build();
        }
        List<Long> fanIds = Objects.requireNonNull(rawSet).stream()
                .map(o -> Long.valueOf(o.toString()))
                .toList();

        GetBaseUserInfoListRespDTO getBaseUserInfoListRespDTO = userRpcService.getBaseUserInfoList(
                GetBaseUserInfoListReqDTO.builder().
                        userIds(fanIds).
                        build()
        );
        if (!getBaseUserInfoListRespDTO.getCode().equals(SUCCESS_CODE)) {
            throw new RemoteException(getBaseUserInfoListRespDTO.getMessage());
        }

        List<UserInfoDTO> userInfoDTOS = getBaseUserInfoListRespDTO.getUserInfos();
        userInfoDTOS.sort((a, b) -> StrUtil.compare(a.getName(), b.getName(), true));
        int totalCount = userInfoDTOS.size();
        int start = (requestParam.getPage() - 1) * requestParam.getPageSize();
        int end = Math.min(start + requestParam.getPageSize(), totalCount);
        List<UserInfoDTO> pageUserInfos = userInfoDTOS.subList(start, end);
        for (UserInfoDTO userInfoDTO : pageUserInfos) {
            boolean isFollow = Boolean.TRUE.equals(redisTemplate.opsForSet().isMember(String.format(USER_FOLLOW_KEY, userInfoDTO.getId()), requestParam.getUserId()));
            userInfoDTO.setIsFollow(isFollow);
        }

        return GetFanListRespDTO.builder().
                code(SUCCESS_CODE).
                message(SUCCESS_MESSAGE).
                page(requestParam.getPage()).
                totalPage(totalCount / requestParam.getPageSize() > 0 ? totalCount / requestParam.getPageSize() : 1).
                totalCount(totalCount).
                fanList(pageUserInfos).
                build();
    }

    @Override
    public GetFansCountRespDTO getFansCount(GetFansCountReqDTO requestParam) {
        this.checkParam(requestParam);

        // 自己快速判断自己是否有被关注过
        if (!followCachePenetrationBloomFilter.contains(requestParam.getUserId())) {
            return GetFansCountRespDTO.builder().
                    code(SUCCESS_CODE).
                    message(SUCCESS_MESSAGE).
                    fansCount(0).
                    build();
        }

        this.checkAndSetRelationKey(requestParam.getUserId(), FAN_LIST);

        Long fansCount = redisTemplate.opsForSet().size(String.format(USER_FAN_KEY, requestParam.getUserId()));

        return GetFansCountRespDTO.builder().
                code(SUCCESS_CODE).
                message(SUCCESS_MESSAGE).
                fansCount(fansCount == null ? 0 : fansCount.intValue()).
                build();
    }

    @Override
    public IsFriendRespDTO isFriend(IsFriendReqDTO requestParam) {
        this.checkParam(requestParam);

        IsFollowRespDTO isAFollowB = this.isFollow(
                IsFollowReqDTO.builder()
                        .fromUserId(requestParam.getUserIdA())
                        .toUserId(requestParam.getUserIdB())
                        .build()
        );
        if (!isAFollowB.getCode().equals(SUCCESS_CODE)) {
            throw new ServiceException(isAFollowB.getMessage());
        }

        IsFollowRespDTO isBFollowA = this.isFollow(
                IsFollowReqDTO.builder()
                       .fromUserId(requestParam.getUserIdB())
                       .toUserId(requestParam.getUserIdA())
                       .build()
        );
        if (!isBFollowA.getCode().equals(SUCCESS_CODE)) {
            throw new ServiceException(isBFollowA.getMessage());
        }

        return IsFriendRespDTO.builder()
                .code(SUCCESS_CODE)
                .message(SUCCESS_MESSAGE)
                .isFriend(isAFollowB.getIsFollow() && isBFollowA.getIsFollow())
                .build();
    }


    public void checkParam(IsFollowReqDTO requestParam) {
        if (requestParam == null) {
            throw new ClientException(REQUEST_PARAM_NULL);
        }
        if (requestParam.getFromUserId() == null || requestParam.getToUserId() == null) {
            throw new ClientException(USER_ID_NULL);
        }
    }

    public void checkParam(FollowActionReqDTO requestParam) {
        if (requestParam == null) {
            throw new ClientException(REQUEST_PARAM_NULL);
        }
        if (requestParam.getFromUserId() == null || requestParam.getToUserId() == null) {
            throw new ClientException(REQUEST_PARAM_NULL);
        }
        if (requestParam.getActionType() == null) {
            throw new ClientException(ACTION_TYPE_NULL);
        }
        if (!RelationActionTypeEnum.isValid(requestParam.getActionType())) {
            throw new ClientException(ACTION_TYPE_INVALID);
        }
    }

    public void checkParam(GetFollowListReqDTO requestParam) {
        if (requestParam == null) {
            throw new ClientException(REQUEST_PARAM_NULL);
        }
        if (requestParam.getUserId() == null) {
            throw new ClientException(USER_ID_NULL);
        }
        if (requestParam.getPage() == null || requestParam.getPage() < 0) {
            throw new ClientException(PAGE_PARAM_ERROR);
        }
        if (requestParam.getPageSize() == null || requestParam.getPageSize() < 0) {
            throw new ClientException(PAGE_PARAM_ERROR);
        }
    }

    public void checkParam(GetFollowsCountReqDTO requestParam) {
        if (requestParam == null) {
            throw new ClientException(REQUEST_PARAM_NULL);
        }
        if (requestParam.getUserId() == null) {
            throw new ClientException(USER_ID_NULL);
        }
    }

    public void checkParam(GetFanListReqDTO requestParam) {
        if (requestParam == null) {
            throw new ClientException(REQUEST_PARAM_NULL);
        }
        if (requestParam.getUserId() == null) {
            throw new ClientException(USER_ID_NULL);
        }
        if (requestParam.getPage() == null || requestParam.getPage() < 0) {
            throw new ClientException(PAGE_PARAM_ERROR);
        }
        if (requestParam.getPageSize() == null || requestParam.getPageSize() < 0) {
            throw new ClientException(PAGE_PARAM_ERROR);
        }
    }

    public void checkParam(GetFansCountReqDTO requestParam) {
        if (requestParam == null) {
            throw new ClientException(REQUEST_PARAM_NULL);
        }
        if (requestParam.getUserId() == null) {
            throw new ClientException(USER_ID_NULL);
        }
    }

    public void checkParam(IsFriendReqDTO requestParam) {
        if (requestParam == null) {
            throw new ClientException(REQUEST_PARAM_NULL);
        }
        if (requestParam.getUserIdA() == null || requestParam.getUserIdB() == null) {
            throw new ClientException(USER_ID_NULL);
        }
    }


    public void actionFollow(Long fromUserId, Long toUserId) {
        String key1 = String.format(USER_FOLLOW_KEY, fromUserId);
        String key2 = String.format(USER_FAN_KEY, toUserId);

        redisTemplate.executePipelined(new SessionCallback<Object>() {
            @Override
            public Object execute(RedisOperations operations) throws DataAccessException {
                operations.opsForSet().add(key1, toUserId);
                operations.expire(key1, USER_FOLLOW_BASE_TTL + ThreadLocalRandom.current().nextInt(31), TimeUnit.SECONDS);
                operations.opsForSet().add(key2, fromUserId);
                operations.expire(key2, USER_FAN_BASE_TTL + ThreadLocalRandom.current().nextInt(31), TimeUnit.SECONDS);
                return null;
            }
        });
    }

    public void actionCancelFollow(Long fromUserId, Long toUserId) {
        String key1 = String.format(USER_FOLLOW_KEY, fromUserId);
        String key2 = String.format(USER_FAN_KEY, toUserId);

        redisTemplate.executePipelined(new SessionCallback<Object>() {
            @Override
            public Object execute(RedisOperations operations) throws DataAccessException {
                operations.opsForSet().remove(key1, toUserId);
                operations.opsForSet().remove(key2, fromUserId);
                return null;
            }
        });
    }

    public void checkAndSetRelationKey(Long userId, String key) {
        switch (key) {
            case FOLLOW_LIST ->{
                boolean hasKey = redisTemplate.hasKey(String.format(USER_FOLLOW_KEY, userId));
                if (hasKey) {
                    return;
                }

                RLock lock = redissonClient.getLock(String.format(LOCK_USER_FOLLOW_KEY, userId));
                lock.lock();
                try {
                    hasKey = redisTemplate.hasKey(String.format(USER_FOLLOW_KEY, userId));
                    if (hasKey) {
                        return;
                    }

                    LambdaQueryWrapper<RelationDO> queryWrapper = Wrappers.lambdaQuery(RelationDO.class)
                            .eq(RelationDO::getFanId, userId);
                    List<RelationDO> relationDOList = baseMapper.selectList(queryWrapper);
                    if (CollectionUtil.isEmpty(relationDOList)) {
                        return;
                    }

                    redisTemplate.opsForSet().add(String.format(USER_FOLLOW_KEY, userId), relationDOList.stream().map(RelationDO::getUserId).toArray());
                    redisTemplate.expire(String.format(USER_FOLLOW_KEY, userId), USER_FOLLOW_BASE_TTL + ThreadLocalRandom.current().nextInt(31), TimeUnit.SECONDS);

                } finally {
                    lock.unlock();
                }
            }
            case FAN_LIST ->{
                boolean hasKey = redisTemplate.hasKey(String.format(USER_FAN_KEY, userId));
                if (hasKey) {
                    return;
                }

                RLock lock = redissonClient.getLock(String.format(LOCK_USER_FAN_KEY, userId));
                lock.lock();
                try {
                    hasKey = redisTemplate.hasKey(String.format(USER_FAN_KEY, userId));
                    if (hasKey) {
                        return;
                    }

                    LambdaQueryWrapper<RelationDO> queryWrapper = Wrappers.lambdaQuery(RelationDO.class)
                            .eq(RelationDO::getUserId, userId);
                    List<RelationDO> relationDOList = baseMapper.selectList(queryWrapper);
                    if (CollectionUtil.isEmpty(relationDOList)) {
                        return;
                    }

                    redisTemplate.opsForSet().add(String.format(USER_FAN_KEY, userId), relationDOList.stream().map(RelationDO::getFanId).toArray());
                    redisTemplate.expire(String.format(USER_FAN_KEY, userId), USER_FAN_BASE_TTL + ThreadLocalRandom.current().nextInt(31), TimeUnit.SECONDS);

                } finally {
                    lock.unlock();
                }
            }
            default ->{
                throw new ServiceException(ACTION_TYPE_INVALID);
            }
        }

    }
}
