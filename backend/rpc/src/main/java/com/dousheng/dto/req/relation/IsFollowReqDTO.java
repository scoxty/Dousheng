package com.dousheng.dto.req.relation;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class IsFollowReqDTO implements Serializable {
    private Long fromUserId;
    private Long toUserId;
}
