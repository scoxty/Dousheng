package com.dousheng.relation.common.enums;


import com.dousheng.relation.common.convention.errorcode.IErrorCode;

public enum RelationErrorCodeEnum implements IErrorCode {
    USER_PARAM_ERROR("A000300", "参数校验失败"),
    REQUEST_PARAM_NULL("A000301", "请求参数不能为空"),
    USER_ID_NULL("A000302", "用户id不能为空"),
    NO_PERMISSION("A000303", "没有权限"),
    PAGE_PARAM_ERROR("A000304", "请输入正确的分页参数"),
    ACTION_TYPE_NULL("A000305", "操作类型不能为空"),
    ACTION_TYPE_INVALID("A000306", "非法操作类型"),

    USER_NOT_EXIST("B000300", "用户不存在"),
    FOLLOW_NOT_EXIST("B000500", "关注关系不存在"),
    SEND_MESSAGE_ERROR("B000600", "发送消息失败"),
    REPEAT_FOLLOW("B000301", "重复关注"),
    REPEAT_CANCEL_FOLLOW("B000302", "重复取消关注");

    private final String code;

    private final String message;

    RelationErrorCodeEnum(String code, String message) {
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
