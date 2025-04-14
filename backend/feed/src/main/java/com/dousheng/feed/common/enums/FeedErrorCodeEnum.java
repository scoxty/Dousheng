package com.dousheng.feed.common.enums;

import com.dousheng.feed.common.convention.errorcode.IErrorCode;

public enum FeedErrorCodeEnum implements IErrorCode {
    VIDEO_INFO_IS_NULL("A000300", "视频信息为空"),
    VIDEO_ID_IS_NULL("A000301", "视频ID为空"),
    LIKE_COUNTS_IS_NULL("A000302", "点赞数为空"),
    COMMENT_COUNTS_IS_NULL("A000303", "评论数为空"),
    CREATE_TIME_IS_NULL("A000304", "创建时间为空"),

    FEED_LIST_EMPTY("B000300", "视频列表为空");

    private final String code;
    private final String message;

    FeedErrorCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }
}
