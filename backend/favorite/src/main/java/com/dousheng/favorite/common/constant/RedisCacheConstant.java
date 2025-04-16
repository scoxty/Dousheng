package com.dousheng.favorite.common.constant;

import java.util.concurrent.TimeUnit;

public class RedisCacheConstant {
    public static final String LOCK_USER_FAVORITE_KEY = "dousheng:lock_user-favorite:%d";
    public static final String USER_FAVORITE_KEY = "dousheng:user-favorite:%d";
    public static final long USER_FAVORITE_BASE_TTL = TimeUnit.DAYS.toSeconds(7);
    public static final int MAX_CACHE_SIZE = 500;

    public static final String LOCK_USER_LIKED_COUNT_KEY = "dousheng:lock_user-liked-count:%d";
    public static final String USER_LIKED_COUNT_KEY = "dousheng:user-like-count:%d";
    public static final long  USER_LIKED_COUNT_BASE_TTL = TimeUnit.DAYS.toSeconds(7);

    public static final String LOCK_VIDEO_LIKED_COUNT_KEY = "dousheng:lock_video-liked-count:%d";
    public static final String VIDEO_LIKED_COUNT_KEY = "dousheng:video-like-count:%d";
    public static final long VIDEO_LIKED_COUNT_BASE_TTL = TimeUnit.DAYS.toSeconds(7);
}
