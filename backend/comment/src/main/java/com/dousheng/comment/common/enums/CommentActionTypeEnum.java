package com.dousheng.comment.common.enums;

public enum CommentActionTypeEnum {
    COMMENT(1, "评论"),
    CANCEL_COMMENT(0, "取消评论");

    public final Integer type;
    public final String value;

    CommentActionTypeEnum(Integer type, String value) {
        this.type = type;
        this.value = value;
    }

    public static boolean isValid(Integer type) {
        return type != null && (type.equals(COMMENT.type) || type.equals(CANCEL_COMMENT.type));
    }
}
