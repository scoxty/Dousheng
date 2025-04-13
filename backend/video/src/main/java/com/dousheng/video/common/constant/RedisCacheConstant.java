package com.dousheng.video.common.constant;

import java.util.concurrent.TimeUnit;

public class RedisCacheConstant {
    public static final String LOCK_GET_PUBLISH_LIST_BY_AUTHORID_KEY = "dousheng:lock_get-publish-list:%d:%d"; // userId + isPrivate两个维度
    public static final String GET_PUBLISH_LIST_KEY = "dousheng:get-publish-list:%d:%d"; // userId + isPrivate两个维度
    public static final long GET_PUBLISH_LIST_BASE_TTL = TimeUnit.DAYS.toSeconds(7); // 7 天

    public static final String LOCK_GET_WORKCOUNT_BY_AUTHORID_KEY = "dousheng:lock_get-workcount:%d";
    public static final String GET_WORKCOUNT_BY_AUTHORID_KEY = "dousheng:get-workcount:%d";
    public static final long GET_WORKCOUNT_BASE_TTL = TimeUnit.DAYS.toSeconds(7);


}
