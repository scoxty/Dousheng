package com.dousheng.auth.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dousheng.auth.common.convention.exception.ClientException;
import com.dousheng.auth.common.convention.exception.ServiceException;
import com.dousheng.auth.common.enums.AuthErrorCodeEnum;
import com.dousheng.auth.dao.entity.UserDO;
import com.dousheng.auth.dao.mapper.UserMapper;
import com.dousheng.auth.service.AuthService;
import com.dousheng.dto.common.UserInfoDTO;
import com.dousheng.dto.req.auth.AuthenticateReqDTO;
import com.dousheng.dto.req.auth.RegisterReqDTO;
import com.dousheng.dto.req.auth.LoginReqDTO;
import com.dousheng.dto.req.auth.LogoutReqDTO;
import com.dousheng.dto.resp.auth.AuthenticateRespDTO;
import com.dousheng.dto.resp.auth.RegisterRespDTO;
import com.dousheng.dto.resp.auth.LoginRespDTO;
import com.dousheng.dto.resp.auth.LogoutRespDTO;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

import static com.dousheng.auth.common.constant.RedisCacheConstant.LOCK_USER_REGISTER_KEY;
import static com.dousheng.auth.common.constant.RedisCacheConstant.USER_LOGIN_KEY;
import static com.dousheng.auth.common.constant.SuccessBaseRespConstant.SUCCESS_CODE;
import static com.dousheng.auth.common.constant.SuccessBaseRespConstant.SUCCESS_MESSAGE;
import static com.dousheng.auth.common.enums.AuthErrorCodeEnum.*;
import static com.dousheng.auth.common.enums.SexEnum.SECRET;
import static com.dousheng.auth.common.enums.YesOrNoEnum.YES;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl extends ServiceImpl<UserMapper, UserDO> implements AuthService {

    private final RBloomFilter<String> usernameCachePenetrationBloomFilter;
    private final RedissonClient redissonClient;
    private final RedisTemplate redisTemplate;

    @Override
    public AuthenticateRespDTO authenticate(AuthenticateReqDTO requestParam) {
        this.checkParam(requestParam);

        String token = (String) redisTemplate.opsForValue().get(USER_LOGIN_KEY + requestParam.getUserId());

        if (StrUtil.compare(token, requestParam.getToken(), true) != 0) {
            throw new ClientException(USER_AUTH_ERROR);
        }

        AuthenticateRespDTO respDTO = AuthenticateRespDTO.builder().
                code(SUCCESS_CODE).
                message(SUCCESS_MESSAGE).build();

        return respDTO;
    }

    @Override
    public RegisterRespDTO register(RegisterReqDTO requestParam) {
        this.checkParam(requestParam);

        if (this.isUsernameExist(requestParam.getUsername())) {
            throw new ClientException(USER_REGISTER_USERNAME_EXISTS); // 误判率在可接受范围
        }

        // 加锁防止多用户注册相同用户名导致冲突
        RLock lock = redissonClient.getLock(LOCK_USER_REGISTER_KEY + requestParam.getUsername());
        if (!lock.tryLock()) {
            throw new ClientException(USER_REGISTER_USERNAME_EXISTS);
        }

        try {
            int inserted = baseMapper.insert(this.buildDefaultUser(requestParam));
            if (inserted < 1) {
                throw new ServiceException(USER_REGISTER_SAVE_ERROR);
            }
            usernameCachePenetrationBloomFilter.add(requestParam.getUsername());
        } catch (DuplicateKeyException ex) {
            throw new ClientException(USER_REGISTER_USERNAME_EXISTS);
        } finally {
            lock.unlock();
        }

        RegisterRespDTO respDTO = RegisterRespDTO.builder().
                code(SUCCESS_CODE).
                message(SUCCESS_MESSAGE).build();

        return respDTO;
    }

    @Override
    public LoginRespDTO login(LoginReqDTO requestParam) {
        this.checkParam(requestParam);

        if (!this.isUsernameExist(requestParam.getUsername())) {
            throw new ClientException(USER_LOGIN_USERNAME_NOT_EXISTS);
        }

        LambdaQueryWrapper<UserDO> queryWrapper = Wrappers.lambdaQuery(UserDO.class)
                .eq(UserDO::getName, requestParam.getUsername())
                .eq(UserDO::getPassword, requestParam.getPassword());
        UserDO userDO = baseMapper.selectOne(queryWrapper);
        if (userDO == null) {
            throw new ClientException(USER_LOGIN_USERNAME_NOT_EXISTS);
        }

        if (this.isLogin(userDO.getId())) {
            throw new ClientException(USER_REPEATED_LOGIN);
        }

        String uuid = UUID.randomUUID().toString();
        redisTemplate.opsForValue().set(USER_LOGIN_KEY + userDO.getId(), uuid);

        UserInfoDTO userInfo = BeanUtil.toBean(userDO, UserInfoDTO.class);
        userInfo.setUserToken(uuid);
        LoginRespDTO respDTO = LoginRespDTO.builder().
                code(SUCCESS_CODE).
                message(SUCCESS_MESSAGE).
                userInfo(userInfo).build();

        return respDTO;
    }

    @Override
    public LogoutRespDTO logout(LogoutReqDTO requestParam) {
        this.checkParam(requestParam);
        if (!this.isLogin(requestParam.getUserId())) {
            throw new ClientException(USER_NOT_LOGIN);
        }
        redisTemplate.delete(USER_LOGIN_KEY + requestParam.getUserId());
        LogoutRespDTO respDTO = LogoutRespDTO.builder().
                code(SUCCESS_CODE).
                message(SUCCESS_MESSAGE).build();
        return respDTO;
    }

    public void checkParam(RegisterReqDTO requestParam) {
        String username = requestParam.getUsername();
        String password = requestParam.getPassword();

        if (StrUtil.isBlank(username)) {
            throw new ClientException(AuthErrorCodeEnum.USER_USERNAME_NULL);
        }

        if (username.length() > 32) {
            throw new ClientException(AuthErrorCodeEnum.USER_USERNAME_TOO_LONG);
        }

        if (StrUtil.isBlank(password)) {
            throw new ClientException(AuthErrorCodeEnum.USER_PASSWORD_NULL);
        }

        if (password.length() > 32) {
            throw new ClientException(AuthErrorCodeEnum.USER_PASSWORD_TOO_LONG);
        }
    }

    public void checkParam(LoginReqDTO requestParam) {
        String username = requestParam.getUsername();
        String password = requestParam.getPassword();

        if (StrUtil.isBlank(username)) {
            throw new ClientException(AuthErrorCodeEnum.USER_USERNAME_NULL);
        }

        if (username.length() > 32) {
            throw new ClientException(AuthErrorCodeEnum.USER_USERNAME_TOO_LONG);
        }

        if (StrUtil.isBlank(password)) {
            throw new ClientException(AuthErrorCodeEnum.USER_PASSWORD_NULL);
        }

        if (password.length() > 32) {
            throw new ClientException(AuthErrorCodeEnum.USER_PASSWORD_TOO_LONG);
        }
    }

    public void checkParam(LogoutReqDTO requestParam) {
        if (requestParam.getUserId() == null || requestParam.getUserId().equals(0L)) {
            throw new ClientException(AuthErrorCodeEnum.USER_ID_NULL);
        }
    }

    public void checkParam(AuthenticateReqDTO requestParam) {
        if (requestParam.getUserId() == null || requestParam.getUserId().equals(0L)) {
            throw new ClientException(AuthErrorCodeEnum.USER_ID_NULL);
        }

        if (StrUtil.isBlank(requestParam.getToken())) {
            throw new ClientException(AuthErrorCodeEnum.USER_TOKEN_NULL);
        }
    }

    public boolean isUsernameExist(String username) {
        return usernameCachePenetrationBloomFilter.contains(username);
    }

    public UserDO buildDefaultUser(RegisterReqDTO requestParam) {
        String doushengNum = "DS" + UUID.randomUUID().toString().replace("-", "").substring(0, 8).toUpperCase();
        // TODO: 用AI自动生成
        String signature = "这家伙很懒，什么都没留下~";
        String avatar = "https://dousheng-xty.oss-cn-guangzhou.aliyuncs.com/picture/default-avatar.png";
        String backgroundImage = "https://dousheng-xty.oss-cn-guangzhou.aliyuncs.com/picture/default-background_image.png";
        Date now = new Date();
        UserDO userDO = UserDO.builder().
                id(IdWorker.getId()).
                name(requestParam.getUsername()).
                password(requestParam.getPassword()).
                sex(SECRET.type).
                doushengNum(doushengNum).
                birthday(now).
                country("中国").
                signature(signature).
                avatar(avatar).
                backgroundImage(backgroundImage).
                canDoushengNumBeUpdated(YES.type).build();

        return userDO;
    }

    public boolean isLogin(Long userId) {
        String token = (String) redisTemplate.opsForValue().get(USER_LOGIN_KEY + userId);
        return !StrUtil.isBlank(token);
    }
}
