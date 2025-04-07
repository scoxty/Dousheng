package com.dousheng.dto.resp.auth;

import com.dousheng.dto.common.UserInfoDTO;
import lombok.Data;

import java.io.Serializable;

@Data
public class LoginRespDTO implements Serializable {
    private String code;
    private String message;
    private UserInfoDTO userInfo;
}
