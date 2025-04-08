package com.dousheng.dto.resp.user;

import com.dousheng.dto.common.UserInfoDTO;
import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ModifyImageRespDTO implements Serializable {
    private String code;
    private String message;
    private UserInfoDTO userInfo;
}
