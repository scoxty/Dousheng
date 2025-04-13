package com.dousheng.dto.resp.comment;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GetVideoCommentCountsRespDTO implements Serializable {
    private String code;
    private String message;
    private Integer commentCounts;
}
