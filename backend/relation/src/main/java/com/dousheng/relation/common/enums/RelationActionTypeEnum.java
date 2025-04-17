package com.dousheng.relation.common.enums;

public enum RelationActionTypeEnum {
    FOLLOW(1, "关注"),
    UNFOLLOW(0, "取消关注");

    public final Integer type;
    public final String value;

    RelationActionTypeEnum(Integer type, String value) {
        this.type = type;
        this.value = value;
    }

    public static boolean isValid(Integer type) {
        return type != null && (type.equals(FOLLOW.type) || type.equals(UNFOLLOW.type));
    }
}
