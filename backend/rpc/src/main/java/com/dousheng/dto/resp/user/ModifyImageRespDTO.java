package com.dousheng.dto.resp.user;

import com.dousheng.dto.common.UserInfoDTO;
import lombok.Data;

import java.io.Serializable;

@Data
public class ModifyImageRespDTO implements Serializable {
    private String code;
    private String message;
    private UserInfoDTO userInfo;
}
