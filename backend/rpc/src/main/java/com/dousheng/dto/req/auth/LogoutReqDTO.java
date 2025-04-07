package com.dousheng.dto.req.auth;

import lombok.Data;

import java.io.Serializable;

@Data
public class LogoutReqDTO implements Serializable {
    private String userId;
}
