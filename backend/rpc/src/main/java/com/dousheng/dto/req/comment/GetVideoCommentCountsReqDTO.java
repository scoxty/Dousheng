package com.dousheng.dto.req.comment;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GetVideoCommentCountsReqDTO {
    private Long videoId;
}
