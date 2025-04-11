package com.dousheng.dto.resp.comment;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GetVideoCommentCountsRespDTO {
    private String code;
    private String message;
    private Integer commentCounts;
}
