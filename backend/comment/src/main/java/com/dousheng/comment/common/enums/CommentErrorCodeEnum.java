package com.dousheng.comment.common.enums;

import com.dousheng.comment.common.convention.errorcode.IErrorCode;

public enum CommentErrorCodeEnum implements IErrorCode {
    USER_PARAM_ERROR("A000300", "参数校验失败"),
    REQUEST_PARAM_NULL("A000301", "请求参数不能为空"),
    USER_ID_NULL("A000302", "用户id不能为空"),
    NO_PERMISSION("A000303", "没有权限"),
    PAGE_PARAM_ERROR("A000304", "请输入正确的分页参数"),
    ACTION_TYPE_NULL("A000305", "操作类型不能为空"),
    ACTION_TYPE_INVALID("A000306", "非法操作类型"),
    VIDEO_ID_NULL("A000307", "视频id不能为空"),
    CONTENT_NULL("A000308", "评论内容不能为空"),
    AUTHOR_ID_NULL("A000309", "作者id不能为空"),
    COMMENT_ID_NULL("A000310", "评论id不能为空"),

    VIDEO_NOT_EXIST("B000300", "视频不存在"),
    COMMENT_NOT_EXIST("B000500", "评论不存在"),
    SEND_MESSAGE_ERROR("B000600", "发送消息失败"),

    MQ_OPERATE_DB_ERROR("B000800", "操作数据库失败"),
    MQ_SAVE_COMMENT_ERROR("B000801", "保存评论失败"),
    MQ_DEL_COMMENT_ERROR("B000802", "删除评论失败");

    private final String code;

    private final String message;

    CommentErrorCodeEnum(String code, String message) {
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
