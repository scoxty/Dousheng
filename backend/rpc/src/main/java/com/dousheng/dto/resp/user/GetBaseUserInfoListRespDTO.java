package com.dousheng.dto.resp.user;

import com.dousheng.dto.common.UserInfoDTO;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GetBaseUserInfoListRespDTO implements Serializable {
    private String code;
    private String message;
    private List<UserInfoDTO> userInfos;
}
