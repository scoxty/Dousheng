package com.dousheng.auth.common.enums;

import com.dousheng.auth.common.convention.errorcode.IErrorCode;

public enum AuthErrorCodeEnum implements IErrorCode {
    USER_PARAM_ERROR("A000300", "参数错误"),
    USER_USERNAME_NULL("A000301", "用户名不能为空"),
    USER_PASSWORD_NULL("A000302", "密码不能为空"),
    USER_USERNAME_TOO_LONG("A000303", "用户名太长"),
    USER_PASSWORD_TOO_LONG("A000304", "密码太长"),
    USER_REGISTER_USERNAME_EXISTS("A000305", "用户名已存在"),
    USER_LOGIN_USERNAME_NOT_EXISTS("A000306", "用户名不存在"),
    USER_ID_NULL("A000307", "用户ID不能为空"),
    USER_TOKEN_NULL("A000308", "Token不能为空"),

    USER_REPEATED_LOGIN("A000400", "重复登录"),
    USER_NOT_LOGIN("A000401", "未登录"),

    USER_AUTH_ERROR("A000500", "认证失败"),

    USER_REGISTER_SERVER_ERROR("B000300", "用户注册失败"),
    USER_REGISTER_SAVE_ERROR("B000301", "新增用户失败");

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
