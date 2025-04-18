package com.dousheng.comment.mq.message;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class InteractionIndicatorMsg implements Serializable {
    private String source;
    private Integer actionType;
    private Long userId;
    private Long videoId;
}
