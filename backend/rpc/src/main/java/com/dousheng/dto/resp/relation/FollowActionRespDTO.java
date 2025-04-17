package com.dousheng.dto.resp.relation;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FollowActionRespDTO implements Serializable {
    private String code;
    private String message;
}
