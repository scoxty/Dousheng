package com.dousheng.user.common.constant;

public class RedisCacheConstant {
    public static final String LOCK_GET_USERID_BY_NAME_KEY = "dousheng:lock_get-userid-by-name:%s";
    public static final String LOCK_GET_USERINFO_BY_ID_KEY = "dousheng:lock_get-userinfo-by-id:%d";

    public static final String USER_INFO_KEY = "dousheng:user-info-by-id:%d";
    public static final long USER_INFO_BASE_TTL = 60 * 60 * 24 * 7; // 7 天

    public static final String USER_ID_KEY = "dousheng:user-id-by-name:%s";
    public static final long USER_ID_BASE_TTL = 60 * 60 * 24 * 7; // 7 天
}
