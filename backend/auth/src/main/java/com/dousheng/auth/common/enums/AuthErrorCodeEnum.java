package com.dousheng.auth.common.enums;

import com.dousheng.auth.common.convention.errorcode.IErrorCode;

public enum AuthErrorCodeEnum implements IErrorCode {
    PARAM_ERROR("A000300", "参数校验错误"),
    REQUEST_PARAM_NULL("A0003001", "请求参数为空"),
    USERNAME_NULL("A000302", "用户名不能为空"),
    PASSWORD_NULL("A000303", "密码不能为空"),
    USERNAME_TOO_LONG("A000304", "用户名太长"),
    PASSWORD_TOO_LONG("A000305", "密码太长"),
    REGISTER_USERNAME_EXISTS("A000306", "用户名已存在"),
    LOGIN_USERNAME_NOT_EXISTS("A000307", "用户名不存在"),
    USERID_NULL("A000308", "用户ID不能为空"),
    TOKEN_NULL("A000309", "Token不能为空"),

    USER_REPEATED_LOGIN("A000400", "重复登录"),
    USER_NOT_LOGIN("A000401", "未登录"),

    LOGIN_ERROR("A000500", "登录失败"),
    PASSWORD_ERROR("A000501", "密码错误"),


    AUTH_ERROR("A000600", "认证失败"),

    REGISTER_SERVER_ERROR("B000300", "用户注册失败"),
    REGISTER_SAVE_USER_ERROR("B000301", "新增用户失败");

    private final String code;

    private final String message;

    AuthErrorCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }
}
