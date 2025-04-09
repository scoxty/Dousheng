package com.dousheng.dto.resp.favorite;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GetTotalLikeMeCountRespDTO implements Serializable {
    private String code;
    private String message;
    private Integer totalLikeMeCount;
}
