package com.dousheng.dto.req.favorite;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class IsLikeVideoReqDTO implements Serializable {
    private Long userId;
    private Long videoId;
}
