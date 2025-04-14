package com.dousheng.dto.resp.favorite;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class IsLikeVideoRespDTO implements Serializable {
    private String code;
    private String message;
    private Boolean isLike;
}
