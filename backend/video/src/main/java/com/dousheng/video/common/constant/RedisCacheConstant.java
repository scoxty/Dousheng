package com.dousheng.video.common.constant;

import java.util.concurrent.TimeUnit;

public class RedisCacheConstant {
    public static final String LOCK_GET_PUBLIC_LIST_BY_AUTHORID_KEY = "dousheng:lock_get-public-list:%d";
    public static final String GET_PUBLIC_LIST_KEY = "dousheng:get-public-list:%d";
    public static final long GET_PUBLIC_LIST_BASE_TTL = TimeUnit.DAYS.toSeconds(7); // 7 天

    public static final String LOCK_GET_WORKCOUNT_BY_AUTHORID_KEY = "dousheng:lock_get-workcount:%d";
    public static final String GET_WORKCOUNT_BY_AUTHORID_KEY = "dousheng:get-workcount:%d";
    public static final long GET_WORKCOUNT_BASE_TTL = TimeUnit.DAYS.toSeconds(7);

    public static final String LOCK_GET_VIDEO_DETAIL_BY_VIDEOID_KEY = "dousheng:lock_get-video-detail:%d";
    public static final String GET_VIDEO_DETAIL_BY_VIDEOID_KEY = "dousheng:get-video-detail:%d";
    public static final long GET_VIDEO_DETAIL_BASE_TTL = TimeUnit.DAYS.toSeconds(1); // 1 天


}
