package com.dousheng.feed.mq.message;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class InteractionIndicatorMsg {
    private String source;
    private Integer actionType;
    private Long userId;
    private Long videoId;
}
