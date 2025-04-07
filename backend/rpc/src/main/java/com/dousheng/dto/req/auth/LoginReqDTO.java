package com.dousheng.dto.req.auth;

import lombok.Data;

import java.io.Serializable;

@Data
public class LoginReqDTO implements Serializable {
    private String mobile;
    private String smsCode;
}
