package com.dousheng.comment.common.constant;

import java.util.concurrent.TimeUnit;

public class RedisCacheConstant {
    public static final String LOCK_COMMENT_COUNT_KEY = "dousheng:lock_comment-count:%d";
    public static final String COMMENT_COUNT_KEY = "dousheng:comment-count:%d";
    public static final long COMMENT_COUNT_BASE_TTL = TimeUnit.DAYS.toSeconds(7);
}
