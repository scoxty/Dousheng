package com.dousheng.dto.req.video;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DelVideoReqDTO implements Serializable {
    private Long userId;
    private Long videoId;
    private Long authorId;
}
