package com.dousheng.user.common.enums;

public enum UserInfoModifyTypeEnum {
    NAME(1, "昵称"),
    DOUSHENG_NUM(2, "抖声号"),
    SEX(3, "性别"),
    BIRTHDAY(4, "生日"),
    LOCATION(5, "所在地"),
    SIGNATURE(6, "简介");

    public final Integer type;
    public final String value;

    UserInfoModifyTypeEnum(Integer type, String value) {
        this.type = type;
        this.value = value;
    }

    public static boolean isValid(Integer type) {
        return type.equals(UserInfoModifyTypeEnum.NAME.type) ||
                type.equals(UserInfoModifyTypeEnum.DOUSHENG_NUM.type) ||
                type.equals(UserInfoModifyTypeEnum.SEX.type) ||
                type.equals(UserInfoModifyTypeEnum.BIRTHDAY.type) ||
                type.equals(UserInfoModifyTypeEnum.LOCATION.type) ||
                type.equals(UserInfoModifyTypeEnum.SIGNATURE.type);
    }
}