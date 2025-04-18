package com.dousheng.dto.req.relation;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class IsFriendReqDTO implements Serializable {
    private Long userIdA;
    private Long userIdB;
}
