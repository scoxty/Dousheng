package com.dousheng.user.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dousheng.dto.common.UserInfoDTO;
import com.dousheng.dto.req.favorite.GetTotalLikeMeCountReqDTO;
import com.dousheng.dto.req.relation.GetFansCountReqDTO;
import com.dousheng.dto.req.relation.GetFollowsCountReqDTO;
import com.dousheng.dto.req.user.*;
import com.dousheng.dto.resp.favorite.GetTotalLikeMeCountRespDTO;
import com.dousheng.dto.resp.relation.GetFansCountRespDTO;
import com.dousheng.dto.resp.relation.GetFollowsCountRespDTO;
import com.dousheng.dto.resp.user.*;
import com.dousheng.service.FavoriteRpcService;
import com.dousheng.service.RelationRpcService;
import com.dousheng.user.common.convention.exception.ClientException;
import com.dousheng.user.common.convention.exception.RemoteException;
import com.dousheng.user.common.enums.UserInfoModifyImageTypeEnum;
import com.dousheng.user.common.enums.SexEnum;
import com.dousheng.user.common.enums.UserInfoModifyTypeEnum;
import com.dousheng.user.dao.entity.UserDO;
import com.dousheng.user.dao.mapper.UserMapper;
import com.dousheng.user.service.UserService;
import com.dousheng.user.toolkit.FileUtil;
import com.github.benmanes.caffeine.cache.Cache;
import io.netty.util.internal.ThreadLocalRandom;
import lombok.RequiredArgsConstructor;
import org.apache.dubbo.config.annotation.DubboReference;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;

import static com.dousheng.user.common.constant.RedisCacheConstant.*;
import static com.dousheng.user.common.constant.SuccessBaseRespConstant.SUCCESS_CODE;
import static com.dousheng.user.common.constant.SuccessBaseRespConstant.SUCCESS_MESSAGE;
import static com.dousheng.user.common.convention.errorcode.BaseErrorCode.REMOTE_ERROR;
import static com.dousheng.user.common.enums.UserErrorCodeEnum.*;
import static com.dousheng.user.common.enums.UserInfoModifyImageTypeEnum.AVATAR;
import static com.dousheng.user.common.enums.UserInfoModifyImageTypeEnum.BACKGROUD_IMAGE;
import static com.dousheng.user.common.enums.UserInfoModifyTypeEnum.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements UserService {

    @DubboReference(timeout = 4000, retries = 3, loadbalance = "roundrobin")
    private FavoriteRpcService favoriteRpcService;
    @DubboReference(timeout = 4000, retries = 3, loadbalance = "roundrobin")
    private RelationRpcService relationRpcService;

    private final RedissonClient redissonClient;
    private final RedisTemplate redisTemplate;
    private final Cache<Long, UserDO> userInfoLocalCache;
    private final Cache<String, Long> userIdLocalCache;
    private final RBloomFilter<String> usernameCachePenetrationBloomFilter;
    private final RBloomFilter<Long> userIdCachePenetrationBloomFilter;

    private final ExecutorService executorService = new ThreadPoolExecutor(
            Runtime.getRuntime().availableProcessors() << 1,
            Runtime.getRuntime().availableProcessors() << 2,
            60L,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(200),
            new ThreadPoolExecutor.CallerRunsPolicy());

    @Override
    public GetUserInfoRespDTO getUserInfo(GetUserInfoReqDTO requestParam) {
        this.checkParam(requestParam);

        if (requestParam.getUserId() != null && !userIdCachePenetrationBloomFilter.contains(requestParam.getUserId())) {
            throw new ClientException(USER_NOT_EXISTS);
        }
        if (StrUtil.isNotBlank(requestParam.getUsername()) && !usernameCachePenetrationBloomFilter.contains(requestParam.getUsername())) {
            throw new ClientException(USER_NOT_EXISTS);
        }

        Long userId = requestParam.getUserId();
        if (userId == null) {
            userId = this.getUserIdByName(requestParam.getUsername());
        }

        UserDO baseUserInfo = this.getBaseUserInfoById(userId);
        UserInfoDTO userInfoDTO = BeanUtil.copyProperties(baseUserInfo, UserInfoDTO.class);

        Integer followsCount = 0, fansCount = 0, totalLikeMeCount = 0;
        Future<Integer> FollowsCountFuture = executorService.submit(() -> {
            GetFollowsCountReqDTO reqDTO = GetFollowsCountReqDTO.builder().userId(requestParam.getUserId()).build();
            GetFollowsCountRespDTO respDTO = relationRpcService.getFollowsCount(reqDTO);
            return respDTO.getFollowsCount();
        });
        Future<Integer> fansCountFuture = executorService.submit(() -> {
            GetFansCountReqDTO reqDTO = GetFansCountReqDTO.builder().userId(requestParam.getUserId()).build();
            GetFansCountRespDTO respDTO = relationRpcService.getFansCount(reqDTO);
            return respDTO.getFansCount();
        });
        Future<Integer> totalLikeMeCountFuture = executorService.submit(() -> {
            GetTotalLikeMeCountReqDTO reqDTO = GetTotalLikeMeCountReqDTO.builder().userId(requestParam.getUserId()).build();
            GetTotalLikeMeCountRespDTO respDTO = favoriteRpcService.getTotalLikeMeCount(reqDTO);
            return respDTO.getTotalLikeMeCount();
        });

        try {
            followsCount = FollowsCountFuture.get(4, TimeUnit.SECONDS);
            fansCount = fansCountFuture.get(4, TimeUnit.SECONDS);
            totalLikeMeCount = totalLikeMeCountFuture.get(4, TimeUnit.SECONDS);
        } catch (Exception e) {
            throw new RemoteException(e.getMessage(), REMOTE_ERROR);
        }

        userInfoDTO.setMyFollowsCounts(followsCount);
        userInfoDTO.setMyFansCounts(fansCount);
        userInfoDTO.setTotalLikeMeCounts(totalLikeMeCount);

        GetUserInfoRespDTO respDTO = GetUserInfoRespDTO.builder().
                code(SUCCESS_CODE).
                message(SUCCESS_MESSAGE).
                userInfo(userInfoDTO).
                build();

        return respDTO;
    }

    @Override
    public AddUserRespDTO addUser(AddUserReqDTO requestParam) {
        this.checkParam(requestParam);

        UserDO userDO = BeanUtil.toBean(requestParam.getUserInfo(), UserDO.class);
        userDO.setId(IdWorker.getId());
        try {
            int inserted = baseMapper.insert(userDO);
            if (inserted < 1) {
                throw new ClientException(SAVE_USER_ERROR);
            }
        } catch (DuplicateKeyException ex) {
            throw new ClientException(USER_EXISTS);
        }

        redisTemplate.opsForValue().set(String.format(USER_INFO_KEY, userDO.getId()), JSON.toJSONString(userDO),
                USER_INFO_BASE_TTL + ThreadLocalRandom.current().nextInt(31), TimeUnit.SECONDS);
        userInfoLocalCache.put(userDO.getId(), userDO);
        userIdCachePenetrationBloomFilter.add(userDO.getId());
        usernameCachePenetrationBloomFilter.add(userDO.getName());

        AddUserRespDTO addUserRespDTO = AddUserRespDTO.builder().code(SUCCESS_CODE).message(SUCCESS_MESSAGE).build();

        return addUserRespDTO;
    }

    @Override
    public ModifyUserInfoRespDTO modifyUserInfo(ModifyUserInfoReqDTO requestParam) {
        this.checkParam(requestParam);

        UserDO userDO = new UserDO();
        Integer modifyType = requestParam.getType();
        if (modifyType.equals(NAME.type)) {
            userDO.setName(requestParam.getUserInfo().getName());
        } else if (modifyType.equals(DOUSHENG_NUM.type)) {
            userDO.setDoushengNum(requestParam.getUserInfo().getDoushengNum());
        } else if (modifyType.equals(SEX.type)) {
            userDO.setSex(requestParam.getUserInfo().getSex());
        } else if (modifyType.equals(BIRTHDAY.type)) {
            userDO.setBirthday(requestParam.getUserInfo().getBirthday());
        } else if (modifyType.equals(LOCATION.type)) {
            userDO.setCountry(requestParam.getUserInfo().getCountry());
            userDO.setProvince(requestParam.getUserInfo().getProvince());
            userDO.setCity(requestParam.getUserInfo().getCity());
            userDO.setDistrict(requestParam.getUserInfo().getDistrict());
        } else if (modifyType.equals(SIGNATURE.type)) {
            userDO.setSignature(requestParam.getUserInfo().getSignature());
        }

        LambdaUpdateWrapper<UserDO> updateWrapper = Wrappers.lambdaUpdate(UserDO.class)
                .eq(UserDO::getId, requestParam.getFromUserId());
        baseMapper.update(userDO, updateWrapper);
        
        userInfoLocalCache.invalidate(requestParam.getFromUserId());
        redisTemplate.delete(String.format(USER_INFO_KEY, requestParam.getFromUserId()));

        LambdaQueryWrapper<UserDO> queryWrapper = Wrappers.lambdaQuery(UserDO.class)
                .eq(UserDO::getId, requestParam.getFromUserId());
        UserDO newUserDO = baseMapper.selectOne(queryWrapper);
        ModifyUserInfoRespDTO respDTO = ModifyUserInfoRespDTO.builder().
                code(SUCCESS_CODE).
                message(SUCCESS_MESSAGE).
                userInfo(BeanUtil.copyProperties(newUserDO, UserInfoDTO.class)).
                build();

        return respDTO;
    }

    @Override
    public ModifyImageRespDTO modifyImage(ModifyImageReqDTO requestParam) {
        this.checkParam(requestParam);

        String imageURL;
        try {
            imageURL = FileUtil.uploadPicture(requestParam.getImage());
        } catch (Exception e) {
            throw new RemoteException(e.getMessage(), REMOTE_ERROR);
        }

        UserDO userDO = new UserDO();
        if (requestParam.getType().equals(BACKGROUD_IMAGE.type)) {
            userDO.setBackgroundImage(imageURL);
        } else if (requestParam.getType().equals(AVATAR.type)) {
            userDO.setAvatar(imageURL);
        }
        LambdaUpdateWrapper<UserDO> updateWrapper = Wrappers.lambdaUpdate(UserDO.class)
                .eq(UserDO::getId, requestParam.getToUserId());
        baseMapper.update(userDO, updateWrapper);

        userInfoLocalCache.invalidate(requestParam.getToUserId());
        redisTemplate.delete(String.format(USER_INFO_KEY, requestParam.getToUserId()));

        LambdaQueryWrapper<UserDO> queryWrapper = Wrappers.lambdaQuery(UserDO.class)
                .eq(UserDO::getId, requestParam.getFromUserId());
        UserDO newUserDO = baseMapper.selectOne(queryWrapper);
        ModifyImageRespDTO respDTO = ModifyImageRespDTO.builder().
                code(SUCCESS_CODE).
                message(SUCCESS_MESSAGE).
                userInfo(BeanUtil.copyProperties(newUserDO, UserInfoDTO.class)).
                build();

        return respDTO;
    }

    @Override
    public IsExistRespDTO isExist(IsExistReqDTO requestParam) {
        boolean isExist = true;
        for (Long userId : requestParam.getUserIdList()) {
            if (!userIdCachePenetrationBloomFilter.contains(userId)) {
               isExist = false;
               break;
            }
        }
        return IsExistRespDTO.builder().
                code(SUCCESS_CODE).
                message(SUCCESS_MESSAGE).
                isExist(isExist).
                build();
    }

    @Override
    public GetBaseUserInfoListRespDTO getBaseUserInfoList(GetBaseUserInfoListReqDTO requestParam) {
        this.checkParam(requestParam);

        List<Long> userIds = requestParam.getUserIds();
        List<Long> filteredUserIds = userIds.stream().filter(userIdCachePenetrationBloomFilter::contains).toList();

        List<UserInfoDTO> userInfoDTOS = new ArrayList<>();
        for (Long userId : filteredUserIds) {
            UserDO userDO = this.getBaseUserInfoById(userId);
            userInfoDTOS.add(BeanUtil.copyProperties(userDO, UserInfoDTO.class));
        }

        return GetBaseUserInfoListRespDTO.builder().
                code(SUCCESS_CODE).
                message(SUCCESS_MESSAGE).
                userInfos(userInfoDTOS).
                build();
    }

    public void checkParam(GetBaseUserInfoListReqDTO requestParam) {
        if (requestParam == null) {
            throw new ClientException(REQUEST_PARAM_NULL);
        }
        if (CollectionUtil.isEmpty(requestParam.getUserIds())) {
            throw new ClientException(USER_ID_LIST_NULL);
        }
    }

    public void checkParam(AddUserReqDTO requestParam) {
        if (requestParam == null || requestParam.getUserInfo() == null) {
            throw new ClientException(REQUEST_PARAM_NULL);
        }

        UserInfoDTO userInfo = requestParam.getUserInfo();
        if (StrUtil.isBlank(userInfo.getName())) {
            throw new ClientException(USERNAME_NULL);
        }
        if (StrUtil.isBlank(userInfo.getPassword())) {
            throw new ClientException(PASSWORD_NULL);
        }
        if (StrUtil.isBlank(userInfo.getDoushengNum())) {
            throw new ClientException(DOUSHENG_NUM_NULL);
        }
        if (!SexEnum.isValid(userInfo.getSex())) {
            throw new ClientException(INVALID_SEX);
        }
        if (userInfo.getBirthday() == null) {
            throw new ClientException(BIRTHDAY_NULL);
        }
        if (StrUtil.isBlank(userInfo.getSignature())) {
            throw new ClientException(SIGNATURE_NULL);
        }
        if (StrUtil.isBlank(userInfo.getAvatar())) {
            throw new ClientException(AVATAR_NULL);
        }
        if (userInfo.getCanDoushengNumBeUpdated() == null) {
            throw new ClientException(CAN_UPDATE_DOUSHENG_NUM_NULL);
        }
    }

    public void checkParam(GetUserInfoReqDTO requestParam) {
        if (requestParam == null) {
            throw new ClientException(REQUEST_PARAM_NULL);
        }

        Long userId = requestParam.getUserId();
        String username = requestParam.getUsername();

        // 至少要有一个查询条件
        if ((userId == null || userId == 0L) && (StrUtil.isBlank(username))) {
            throw new ClientException(REQUEST_PARAM_NULL);
        }
    }

    public void checkParam(ModifyUserInfoReqDTO requestParam) {
        if (requestParam == null || requestParam.getUserInfo() == null) {
            throw new ClientException(REQUEST_PARAM_NULL);
        }

        UserInfoDTO userInfo = requestParam.getUserInfo();

        if (requestParam.getFromUserId() == null || requestParam.getFromUserId() == 0L) {
            throw new ClientException(USERID_NULL);
        }
        if (userInfo.getId() == null || userInfo.getId() == 0L) {
            throw new ClientException(USERID_NULL);
        }
        if (!requestParam.getFromUserId().equals(userInfo.getId())) {
            throw new ClientException(NO_PERMISSION_TO_MODIFY);
        }

        if (requestParam.getType() == null) {
            throw new ClientException(MODIFY_TYPE_NULL);
        }
        if (!UserInfoModifyTypeEnum.isValid(requestParam.getType())) {
            throw new ClientException(MODIFY_TYPE_INVALID);
        }

        if (StrUtil.isBlank(userInfo.getName()) &&
                requestParam.getType().equals(UserInfoModifyTypeEnum.NAME.type)) {
            throw new ClientException(USERNAME_NULL);
        }
        if (requestParam.getType().equals(UserInfoModifyTypeEnum.NAME.type) &&
                !userIdCachePenetrationBloomFilter.contains(userInfo.getId())) {
            throw new ClientException(USER_NOT_EXISTS);
        }

        if (StrUtil.isBlank(userInfo.getDoushengNum()) &&
                requestParam.getType().equals(DOUSHENG_NUM.type)) {
            throw new ClientException(DOUSHENG_NUM_NULL);
        }
        if (requestParam.getType().equals(DOUSHENG_NUM.type) &&
                !this.canUpdateDoushengNum(userInfo.getId())) {
            throw new ClientException(NO_PERMISSION_TO_MODIFY);
        }

        if (userInfo.getSex() == null &&
                requestParam.getType().equals(SEX.type)) {
            throw new ClientException(SEX_NULL);
        }
        if (userInfo.getSex() != null && !SexEnum.isValid(userInfo.getSex())) {
            throw new ClientException(INVALID_SEX);
        }

        if (userInfo.getBirthday() == null &&
                requestParam.getType().equals(UserInfoModifyTypeEnum.BIRTHDAY.type)) {
            throw new ClientException(BIRTHDAY_NULL);
        }

        if ((StrUtil.isBlank(userInfo.getCountry()) ||
                StrUtil.isBlank(userInfo.getProvince()) ||
                StrUtil.isBlank(userInfo.getCity())) &&
                requestParam.getType().equals(UserInfoModifyTypeEnum.LOCATION.type)) {
            throw new ClientException(LOCATION_NULL);
        }

        if (StrUtil.isBlank(userInfo.getSignature()) &&
                requestParam.getType().equals(UserInfoModifyTypeEnum.SIGNATURE.type)) {
            throw new ClientException(SIGNATURE_NULL);
        }
    }

    public void checkParam(ModifyImageReqDTO requestParam) {
        if (requestParam == null) {
            throw new ClientException(REQUEST_PARAM_NULL);
        }

        if (requestParam.getFromUserId() == null || requestParam.getFromUserId() == 0L) {
            throw new ClientException(USERID_NULL);
        }
        if (requestParam.getToUserId() == null || requestParam.getToUserId() == 0L) {
            throw new ClientException(USERID_NULL);
        }
        if (!requestParam.getFromUserId().equals(requestParam.getToUserId())) {
            throw new ClientException(NO_PERMISSION_TO_MODIFY);
        }
        if (requestParam.getType() == null) {
            throw new ClientException(MODIFY_TYPE_NULL);
        }
        if (!UserInfoModifyImageTypeEnum.isValid(requestParam.getType())) {
            throw new ClientException(MODIFY_TYPE_INVALID);
        }
        if (requestParam.getImage() == null) {
            throw new ClientException(IMAGE_NULL);
        }
        if (!FileUtil.isImage(requestParam.getImage())) {
            throw new ClientException(INVALID_IMAGE_TYPE);
        }

    }

    public void checkParam(IsExistReqDTO requestParam) {
        if (requestParam == null) {
            throw new ClientException(REQUEST_PARAM_NULL);
        }
        if (CollectionUtil.isEmpty(requestParam.getUserIdList())) {
            throw new ClientException(USER_ID_LIST_NULL);
        }
    }

    private boolean canUpdateDoushengNum(Long userId) {
        LambdaQueryWrapper<UserDO> queryWrapper = Wrappers.lambdaQuery(UserDO.class)
                .eq(UserDO::getId, userId);
        UserDO userDO = baseMapper.selectOne(queryWrapper);
        if (userDO == null) {
            throw new ClientException(USER_NOT_EXISTS);
        }
        return userDO.getCanDoushengNumBeUpdated() == 1;
    }

    public UserDO getBaseUserInfoById(Long id) {
        UserDO userDO = userInfoLocalCache.getIfPresent(id);
        if (userDO != null) {
            return userDO;
        }

        String userInfoStr = (String) redisTemplate.opsForValue().get(String.format(USER_INFO_KEY, id));
        if (StrUtil.isNotBlank(userInfoStr)) {
            userDO = JSON.parseObject(userInfoStr, UserDO.class);
            userInfoLocalCache.put(id, userDO);
            return userDO;
        }

        RLock lock = redissonClient.getLock(String.format(LOCK_GET_USERINFO_BY_ID_KEY, id));
        lock.lock();
        try {
            // double check
            userInfoStr = (String) redisTemplate.opsForValue().get(String.format(USER_INFO_KEY, id));
            if (StrUtil.isNotBlank(userInfoStr)) {
                userDO = JSON.parseObject(userInfoStr, UserDO.class);
                userInfoLocalCache.put(id, userDO);
                return userDO;
            }

            LambdaQueryWrapper<UserDO> queryWrapper = Wrappers.lambdaQuery(UserDO.class)
                    .eq(UserDO::getId, id);
            userDO = baseMapper.selectOne(queryWrapper);
            if (userDO == null) {
                throw new ClientException(USER_NOT_EXISTS);
            }

            redisTemplate.opsForValue().set(String.format(USER_INFO_KEY, id), JSON.toJSONString(userDO),
                    USER_INFO_BASE_TTL + ThreadLocalRandom.current().nextInt(31), TimeUnit.SECONDS);
            userInfoLocalCache.put(userDO.getId(), userDO);
        } finally {
            lock.unlock();
        }

        return userDO;
    }

    public Long getUserIdByName(String username) {
        Long userId = userIdLocalCache.getIfPresent(username);
        if (userId != null) {
            return userId;
        }

        userId = (Long) redisTemplate.opsForValue().get(String.format(USER_ID_KEY, username));
        if (userId != null) {
            userIdLocalCache.put(username, userId);
            return userId;
        }

        RLock lock = redissonClient.getLock(String.format(LOCK_GET_USERID_BY_NAME_KEY, username));
        lock.lock();
        try {
            // double check
            userId = (Long) redisTemplate.opsForValue().get(String.format(USER_ID_KEY, username));
            if (userId != null) {
                userIdLocalCache.put(username, userId);
                return userId;
            }

            LambdaQueryWrapper<UserDO> queryWrapper = Wrappers.lambdaQuery(UserDO.class)
                    .eq(UserDO::getName, username);
            UserDO userDO = baseMapper.selectOne(queryWrapper);
            if (userDO == null) {
                throw new ClientException(USER_NOT_EXISTS);
            }
            userId = userDO.getId();

            redisTemplate.opsForValue().set(String.format(USER_ID_KEY, username), userId,
                    USER_ID_BASE_TTL + ThreadLocalRandom.current().nextInt(31), TimeUnit.SECONDS);
            userIdLocalCache.put(username, userId);
        } finally {
            lock.unlock();
        }

        return userId;
    }
}
