package com.dousheng.user.common.enums;

import com.dousheng.user.common.convention.errorcode.IErrorCode;

public enum UserErrorCodeEnum implements IErrorCode {
    USER_PARAM_ERROR("A000300", "参数校验失败"),
    REQUEST_PARAM_NULL("A000301", "请求参数为空"),
    USERNAME_NULL("A000302", "用户名不能为空"),
    PASSWORD_NULL("A000303", "密码不能为空"),
    DOUSHENG_NUM_NULL("A000304", "抖声号不能为空"),
    SEX_NULL("A000305", "性别不能为空"),
    INVALID_SEX("A000305", "错误性别, 可选范围: 0-女, 1-男, 2-保密"),
    BIRTHDAY_NULL("A000307", "生日不能为空"),
    SIGNATURE_NULL("A000308", "个性签名不能为空"),
    AVATAR_NULL("A000309", "头像不能为空"),
    CAN_UPDATE_DOUSHENG_NUM_NULL("A000310", "是否可以更新抖声号不能为空"),
    NO_PERMISSION_TO_MODIFY("A000311", "无权限修改他人信息"),
    USERID_NULL("A000312", "用户id不能为空"),
    MODIFY_TYPE_NULL("A000313", "修改类型不能为空"),
    MODIFY_TYPE_INVALID("A000314", "修改类型错误"),
    IMAGE_NULL("A000315", "图片不能为空"),
    INVALID_IMAGE_TYPE("A000316", "图片类型错误"),
    PASSWORD_NOT_SUPPORT_UPDATE("A000317", "不支持修改密码"),

    USER_EXISTS("A000400", "用户已存在"),
    USER_NOT_EXISTS("A000401", "用户不存在"),

    SAVE_USER_ERROR("B000300", "新增用户失败"),

    QUERY_USER_ERROR("B000301", "查询用户失败"),

    MODIFY_USER_ERROR("B000302", "修改用户失败"),

    REMOTE_OSS_ERROR("C000100", "调用OSS接口失败"),
    UPLOAD_IMAGE_ERROR("C000101", "上传图片失败");

    private final String code;

    private final String message;

    UserErrorCodeEnum(String code, String message) {
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
