package com.dousheng.dto.req.auth;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AuthenticateReqDTO implements Serializable {
    private Long userId;
    private String token;
}
