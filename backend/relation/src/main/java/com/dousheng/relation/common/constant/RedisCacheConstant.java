package com.dousheng.relation.common.constant;

import java.util.concurrent.TimeUnit;

public class RedisCacheConstant {
    public static final int MAX_CACHE_SIZE = 500;
    public static final String FOLLOW_LIST = "follow_list";
    public static final String FAN_LIST = "fan_list";

    public static final String LOCK_FOLLOW_ACTION_KEY = "dousheng:lock_follow-action:%d";

    public static final String LOCK_USER_FOLLOW_KEY = "dousheng:lock_user-follow:%d";
    public static final String USER_FOLLOW_KEY = "dousheng:user-follow:%d";
    public static final long USER_FOLLOW_BASE_TTL = TimeUnit.DAYS.toSeconds(7);

    public static final String LOCK_USER_FAN_KEY = "dousheng:lock_user-fan:%d";
    public static final String USER_FAN_KEY = "dousheng:user-fan:%d";
    public static final long USER_FAN_BASE_TTL = TimeUnit.DAYS.toSeconds(7);
}
