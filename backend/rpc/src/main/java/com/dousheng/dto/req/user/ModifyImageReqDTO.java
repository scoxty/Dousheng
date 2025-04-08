package com.dousheng.dto.req.user;

import com.dousheng.dto.common.UserInfoDTO;
import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ModifyImageReqDTO implements Serializable {
    private Long fromUserId;
    private Long toUserId;
    private String type;
    private String image;
}
