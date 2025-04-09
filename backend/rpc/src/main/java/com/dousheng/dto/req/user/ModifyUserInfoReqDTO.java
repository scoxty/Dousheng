package com.dousheng.dto.req.user;

import com.dousheng.dto.common.UserInfoDTO;
import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ModifyUserInfoReqDTO implements Serializable {
    private Long fromUserId;
    private Integer type;
    private UserInfoDTO userInfo;
}
