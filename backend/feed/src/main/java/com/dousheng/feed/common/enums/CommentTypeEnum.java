package com.dousheng.feed.common.enums;

public enum CommentTypeEnum {

    COMMENT(1, "评论"),
    CANCEL_COMMENT(2, "取消评论");

    public final Integer type;
    public final String value;

    CommentTypeEnum(Integer type, String value) {
        this.type = type;
        this.value = value;
    }
}
