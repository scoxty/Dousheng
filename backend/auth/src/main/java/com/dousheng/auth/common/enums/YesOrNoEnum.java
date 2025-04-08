package com.dousheng.auth.common.enums;

public enum YesOrNoEnum {
    NO(0, "否"),
    YES(1, "是");

    public final Integer type;
    public final String value;

    YesOrNoEnum(Integer type, String value) {
        this.type = type;
        this.value = value;
    }
}