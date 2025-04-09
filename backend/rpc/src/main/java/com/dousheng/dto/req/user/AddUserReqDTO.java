package com.dousheng.dto.req.user;

import com.dousheng.dto.common.UserInfoDTO;
import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AddUserReqDTO implements Serializable {
    private UserInfoDTO userInfo;
}
