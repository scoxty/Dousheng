package com.dousheng.dto.resp.user;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AddUserRespDTO {
    private String code;
    private String message;
}
