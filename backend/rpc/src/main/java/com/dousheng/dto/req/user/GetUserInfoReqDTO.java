package com.dousheng.dto.req.user;

import lombok.Data;

import java.io.Serializable;

@Data
public class GetUserInfoReqDTO implements Serializable {
    private String userId;
}
