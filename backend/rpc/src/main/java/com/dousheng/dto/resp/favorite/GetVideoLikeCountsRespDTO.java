package com.dousheng.dto.resp.favorite;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GetVideoLikeCountsRespDTO implements Serializable {
    private String code;
    private String message;
    private Integer likeCounts;
}
