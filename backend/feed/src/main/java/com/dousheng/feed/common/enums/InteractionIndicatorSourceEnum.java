package com.dousheng.feed.common.enums;

public enum InteractionIndicatorSourceEnum {
    FAVORITE_SERVICE(1, "favorite-service"),
    COMMENT_SERVICE(2, "comment-service");

    public final Integer type;
    public final String source;

    InteractionIndicatorSourceEnum(Integer type, String source) {
        this.type = type;
        this.source = source;
    }
}
