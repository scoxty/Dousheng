package com.dousheng.video.common.enums;

import com.dousheng.video.common.convention.errorcode.IErrorCode;

public enum VideoErrorCodeEnum implements IErrorCode {
    USER_PARAM_ERROR("A000300", "参数校验失败"),
    REQUEST_PARAM_NULL("A000301", "请求参数不能为空"),
    USER_ID_NULL("A000302", "用户id不能为空"),
    VIDEO_INFO_NULL("A000303", "视频信息不能为空"),
    NO_PERMISSION("A000304", "没有权限"),
    PAGE_PARAM_NULL("A000305", "分页参数不能为空"),
    IS_PRIVATE_NULL("A000306", "是否私密字段不能为空"),
    VIDEO_ID_NULL("A000307", "视频id不能为空"),
    VIDEO_ID_LIST_NULL("A000308", "视频id列表不能为空"),

    VIDEO_NOT_EXIST("B000300", "视频不存在"),
    AUTHOR_NOT_EXIST("B000400", "作者不存在");

    private final String code;

    private final String message;

    VideoErrorCodeEnum(String code, String message) {
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
