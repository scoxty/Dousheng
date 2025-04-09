package com.dousheng.user.common.enums;

public enum UserInfoModifyImageTypeEnum {
    BACKGROUD_IMAGE(1, "用户背景图"),
    AVATAR(2, "用户头像");

    public final Integer type;
    public final String value;

    UserInfoModifyImageTypeEnum(Integer type, String value) {
        this.type = type;
        this.value = value;
    }

    public static boolean isValid(Integer type) {
        return type.equals(BACKGROUD_IMAGE.type) || type.equals(AVATAR.type);
    }
}