package com.dousheng.auth.common.constant;

public class RedisCacheConstant {
    public static final String LOCK_USER_REGISTER_KEY = "dousheng:lock_user-register:";

    public static final String USER_LOGIN_KEY = "dousheng:login:";

    public static final long TOKEN_TTL = 60 * 60 * 24 * 7;
}
