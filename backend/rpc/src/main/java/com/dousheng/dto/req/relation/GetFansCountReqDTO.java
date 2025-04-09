package com.dousheng.dto.req.relation;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GetFansCountReqDTO implements Serializable {
    private Long userId;
}
