package com.dousheng.api.dto.req;

import lombok.Data;

@Data
public class LoginReqDTO {
    private String mobile;
    private String smsCode;
}
