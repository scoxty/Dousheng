package com.dousheng.dto.resp.auth;

import lombok.Data;

import java.io.Serializable;

@Data
public class GetSMSCodeRespDTO implements Serializable {
    private String code;
    private String message;
}
