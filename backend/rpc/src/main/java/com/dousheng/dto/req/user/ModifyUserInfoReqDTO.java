package com.dousheng.dto.req.user;

import com.dousheng.dto.common.UserInfoDTO;
import lombok.Data;

import java.io.Serializable;

@Data
public class ModifyUserInfoReqDTO implements Serializable {
    private String fromUserId;
    private String type;
    private UserInfoDTO userInfo;
}
