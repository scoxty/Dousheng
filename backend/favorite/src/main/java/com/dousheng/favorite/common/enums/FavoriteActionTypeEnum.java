package com.dousheng.favorite.common.enums;

public enum FavoriteActionTypeEnum {
    LIKE(1, "点赞"),
    DISLIKE(0, "取消点赞");

    public final Integer type;
    public final String value;

    FavoriteActionTypeEnum(Integer type, String value) {
        this.type = type;
        this.value = value;
    }

    public static boolean isValid(Integer type) {
        return type != null && (type.equals(LIKE.type) || type.equals(DISLIKE.type));
    }
}
