package com.dousheng.dto.req.user;

import com.dousheng.dto.common.UserInfoDTO;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AddUserReqDTO {
    private UserInfoDTO userInfo;
}
