package com.dousheng.dto.req.auth;

import lombok.Data;

import java.io.Serializable;

@Data
public class GetSMSCodeReqDTO implements Serializable {
    private String mobile;
}
