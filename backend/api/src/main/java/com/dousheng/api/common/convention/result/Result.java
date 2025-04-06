package com.dousheng.api.common.convention.result;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;

/**
 * 全局返回对象
 */
@Data
@Accessors(chain = true)
public class Result<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = 5679018624309023727L;

    /**
     * 正确返回码和返回消息
     */
    public static final String SUCCESS_CODE = "0";
    public static final String SUCCESS_MESSAGE = "success";

    /**
     * 返回码
     */
    private String code;

    /**
     * 返回消息
     */
    private String message;

    /**
     * 请求ID
     */
    private String requestId;

    /**
     * 响应数据
     */
    private T data;

    public boolean isSuccess() {
        return SUCCESS_CODE.equals(code);
    }
}
