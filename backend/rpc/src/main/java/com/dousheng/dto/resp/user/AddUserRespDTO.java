package com.dousheng.dto.resp.user;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AddUserRespDTO implements Serializable {
    private String code;
    private String message;
}
