package com.dousheng.dto.req.auth;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RegisterReqDTO implements Serializable {
    private String username;
    private String password;
}
