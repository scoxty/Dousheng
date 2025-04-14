package com.dousheng.dto.req.video;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GetPrivateListReqDTO implements Serializable {
    private Long userId;
    private Integer page;
    private Integer pageSize;
    private Integer isPrivate;
}
