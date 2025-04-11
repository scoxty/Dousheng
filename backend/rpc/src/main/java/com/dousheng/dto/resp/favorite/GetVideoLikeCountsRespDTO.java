package com.dousheng.dto.resp.favorite;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GetVideoLikeCountsRespDTO {
    private String code;
    private String message;
    private Integer likeCounts;
}
