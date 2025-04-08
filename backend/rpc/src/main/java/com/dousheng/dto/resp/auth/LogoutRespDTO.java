package com.dousheng.dto.resp.auth;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LogoutRespDTO implements Serializable {
    private String code;
    private String message;
}
