package com.dousheng.dto.req.comment;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GetVideoCommentCountsReqDTO implements Serializable {
    private Long videoId;
}
