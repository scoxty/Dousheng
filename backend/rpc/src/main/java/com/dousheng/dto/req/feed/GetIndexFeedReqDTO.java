package com.dousheng.dto.req.feed;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GetIndexFeedReqDTO implements Serializable {
    private Long userId;
    private Integer page;
    private Integer pageSize;
}
