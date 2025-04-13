package com.dousheng.dto.req.favorite;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GetVideoLikeCountsReqDTO implements Serializable {
    private Long videoId;
}
