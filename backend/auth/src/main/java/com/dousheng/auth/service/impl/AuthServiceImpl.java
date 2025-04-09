package com.dousheng.auth.service.impl;

import cn.hutool.core.util.StrUtil;
import com.dousheng.auth.common.convention.exception.ClientException;
import com.dousheng.auth.common.convention.exception.ServiceException;
import com.dousheng.auth.common.enums.AuthErrorCodeEnum;
import com.dousheng.auth.service.AuthService;
import com.dousheng.dto.common.UserInfoDTO;
import com.dousheng.dto.req.auth.AuthenticateReqDTO;
import com.dousheng.dto.req.auth.RegisterReqDTO;
import com.dousheng.dto.req.auth.LoginReqDTO;
import com.dousheng.dto.req.auth.LogoutReqDTO;
import com.dousheng.dto.req.user.AddUserReqDTO;
import com.dousheng.dto.req.user.GetUserInfoReqDTO;
import com.dousheng.dto.resp.auth.AuthenticateRespDTO;
import com.dousheng.dto.resp.auth.RegisterRespDTO;
import com.dousheng.dto.resp.auth.LoginRespDTO;
import com.dousheng.dto.resp.auth.LogoutRespDTO;
import com.dousheng.dto.resp.user.AddUserRespDTO;
import com.dousheng.dto.resp.user.GetUserInfoRespDTO;
import com.dousheng.service.UserRpcService;
import lombok.RequiredArgsConstructor;
import org.apache.dubbo.config.annotation.DubboReference;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
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
public class AuthServiceImpl implements AuthService {

    private final RBloomFilter<String> usernameCachePenetrationBloomFilter;
    private final RedissonClient redissonClient;
    private final RedisTemplate redisTemplate;

    @DubboReference(timeout = 5000, retries = 3, loadbalance = "roundrobin")
    private UserRpcService userRpcService;

    @Override
    public AuthenticateRespDTO authenticate(AuthenticateReqDTO requestParam) {
        this.checkParam(requestParam);

        String token = (String) redisTemplate.opsForValue().get(USER_LOGIN_KEY + requestParam.getUserId());

        if (StrUtil.compare(token, requestParam.getToken(), true) != 0) {
            throw new ClientException(AUTH_ERROR);
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
            throw new ClientException(REGISTER_USERNAME_EXISTS); // 误判率在可接受范围
        }

        // 加锁防止多用户注册相同用户名导致冲突
        RLock lock = redissonClient.getLock(LOCK_USER_REGISTER_KEY + requestParam.getUsername());
        if (!lock.tryLock()) {
            throw new ClientException(REGISTER_USERNAME_EXISTS);
        }
        try {
            AddUserReqDTO addUserReqDTO = AddUserReqDTO.builder().userInfo(this.buildDefaultUser(requestParam)).build();
            AddUserRespDTO addUserRespDTO = userRpcService.addUser(addUserReqDTO);
            if (!addUserRespDTO.getCode().equals(SUCCESS_CODE)) {
                throw new ServiceException(addUserRespDTO.getMessage(), REGISTER_SAVE_USER_ERROR);
            }
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
            throw new ClientException(LOGIN_USERNAME_NOT_EXISTS);
        }

        GetUserInfoReqDTO getUserInfoReqDTO = GetUserInfoReqDTO.builder().username(requestParam.getUsername()).build();
        GetUserInfoRespDTO getUserInfoRespDTO = userRpcService.getUserInfo(getUserInfoReqDTO);
        if (!getUserInfoRespDTO.getCode().equals(SUCCESS_CODE)) {
            throw new ClientException(getUserInfoRespDTO.getMessage(), LOGIN_USERNAME_NOT_EXISTS);
        }
        UserInfoDTO userInfo = getUserInfoRespDTO.getUserInfo();

        if (this.isLogin(userInfo.getId())) {
            throw new ClientException(USER_REPEATED_LOGIN);
        }

        String uuid = UUID.randomUUID().toString();
        redisTemplate.opsForValue().set(USER_LOGIN_KEY + userInfo.getId(), uuid);

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
        if (requestParam == null) {
            throw new ClientException(REQUEST_PARAM_NULL);
        }

        String username = requestParam.getUsername();
        String password = requestParam.getPassword();

        if (StrUtil.isBlank(username)) {
            throw new ClientException(USERNAME_NULL);
        }

        if (username.length() > 32) {
            throw new ClientException(USERNAME_TOO_LONG);
        }

        if (StrUtil.isBlank(password)) {
            throw new ClientException(PASSWORD_NULL);
        }

        if (password.length() > 32) {
            throw new ClientException(PASSWORD_TOO_LONG);
        }
    }

    public void checkParam(LoginReqDTO requestParam) {
        if (requestParam == null) {
            throw new ClientException(REQUEST_PARAM_NULL);
        }

        String username = requestParam.getUsername();
        String password = requestParam.getPassword();

        if (StrUtil.isBlank(username)) {
            throw new ClientException(USERNAME_NULL);
        }

        if (username.length() > 32) {
            throw new ClientException(USERNAME_TOO_LONG);
        }

        if (StrUtil.isBlank(password)) {
            throw new ClientException(PASSWORD_NULL);
        }

        if (password.length() > 32) {
            throw new ClientException(PASSWORD_TOO_LONG);
        }
    }

    public void checkParam(LogoutReqDTO requestParam) {
        if (requestParam == null) {
            throw new ClientException(REQUEST_PARAM_NULL);
        }

        if (requestParam.getUserId() == null || requestParam.getUserId().equals(0L)) {
            throw new ClientException(USERID_NULL);
        }
    }

    public void checkParam(AuthenticateReqDTO requestParam) {
        if (requestParam == null) {
            throw new ClientException(REQUEST_PARAM_NULL);
        }

        if (requestParam.getUserId() == null || requestParam.getUserId().equals(0L)) {
            throw new ClientException(USERID_NULL);
        }

        if (StrUtil.isBlank(requestParam.getToken())) {
            throw new ClientException(TOKEN_NULL);
        }
    }

    public boolean isUsernameExist(String username) {
        return usernameCachePenetrationBloomFilter.contains(username);
    }

    public UserInfoDTO buildDefaultUser(RegisterReqDTO requestParam) {
        String doushengNum = "DS" + UUID.randomUUID().toString().replace("-", "").substring(0, 8).toUpperCase();
        // TODO: 用AI自动生成
        String signature = "这家伙很懒，什么都没留下~";
        String avatar = "https://dousheng-xty.oss-cn-guangzhou.aliyuncs.com/picture/default-avatar.png";
        String backgroundImage = "https://dousheng-xty.oss-cn-guangzhou.aliyuncs.com/picture/default-background_image.png";
        Date now = new Date();
        UserInfoDTO userInfoDTO = UserInfoDTO.builder().
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

        return userInfoDTO;
    }

    public boolean isLogin(Long userId) {
        String token = (String) redisTemplate.opsForValue().get(USER_LOGIN_KEY + userId);
        return !StrUtil.isBlank(token);
    }
}
