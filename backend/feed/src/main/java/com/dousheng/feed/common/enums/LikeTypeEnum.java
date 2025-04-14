package com.dousheng.feed.common.enums;

public enum LikeTypeEnum {
    LIKE(1, "点赞"),
    DISLIKE(2, "点踩");

    public final Integer type;
    public final String value;

    LikeTypeEnum(Integer type, String value) {
        this.type = type;
        this.value = value;
    }
}
