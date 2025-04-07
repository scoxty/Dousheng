package com.dousheng.dto.req.user;

import com.dousheng.dto.common.UserInfoDTO;
import lombok.Data;

import java.io.Serializable;

@Data
public class ModifyImageReqDTO implements Serializable {
    private String fromUserId;
    private String toUserId;
    private String type;
    private String image;
}
