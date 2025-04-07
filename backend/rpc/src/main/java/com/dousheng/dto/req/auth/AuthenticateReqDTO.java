package com.dousheng.dto.req.auth;

import lombok.Data;

import java.io.Serializable;

@Data
public class AuthenticateReqDTO implements Serializable {
    private String userId;
    private String token;
}
