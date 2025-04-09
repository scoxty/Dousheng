package com.dousheng.user.common.enums;

public enum SexEnum {
    WOMAN(0, "女"),
    MAN(1, "男"),
    SECRET(2, "保密");

    public final Integer type;
    public final String value;

    SexEnum(Integer type, String value) {
        this.type = type;
        this.value = value;
    }

    public static boolean isValid(Integer type) {
        return type.equals(WOMAN.type) || type.equals(MAN.type) || type.equals(SECRET.type);
    }
}
