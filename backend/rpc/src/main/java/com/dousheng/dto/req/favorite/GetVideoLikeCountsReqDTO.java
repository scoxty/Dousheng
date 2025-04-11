package com.dousheng.dto.req.favorite;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GetVideoLikeCountsReqDTO {
    private Long videoId;
}
