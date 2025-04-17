package com.dousheng.favorite.common.enums;

import com.dousheng.favorite.common.convention.errorcode.IErrorCode;

public enum FavoriteErrorCodeEnum implements IErrorCode {
    USER_PARAM_ERROR("A000300", "参数校验失败"),
    REQUEST_PARAM_NULL("A000301", "请求参数不能为空"),
    USER_ID_NULL("A000302", "用户id不能为空"),
    VIDEO_INFO_NULL("A000303", "视频信息不能为空"),
    NO_PERMISSION("A000304", "没有权限"),
    PAGE_PARAM_NULL("A000305", "分页参数不能为空"),
    VIDEO_ID_NULL("A000307", "视频id不能为空"),
    ACTION_TYPE_NULL("A000308", "操作类型不能为空"),
    ACTION_TYPE_INVALID("A000309", "非法操作类型"),

    VIDEO_NOT_EXIST("B000300", "视频不存在"),
    AUTHOR_NOT_EXIST("B000400", "作者不存在"),
    FAVORITE_NOT_EXIST("B000500", "点赞关系不存在"),
    SEND_MESSAGE_ERROR("B000600", "发送消息失败"),

    MQ_SAVE_DB_ERROR("B000700", "保存数据库失败"),
    MQ_SAVE_FAVORITE_ERROR("B000701", "保存点赞关系失败"),
    MQ_DEL_FAVORITE_ERROR("B000702", "删除点赞关系失败");

    private final String code;

    private final String message;

    FavoriteErrorCodeEnum(String code, String message) {
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
