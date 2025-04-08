package com.dousheng.dto.req.user;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GetUserInfoReqDTO implements Serializable {
    private Long userId;
}
