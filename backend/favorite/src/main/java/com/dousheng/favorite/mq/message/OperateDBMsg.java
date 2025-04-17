package com.dousheng.favorite.mq.message;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OperateDBMsg implements Serializable {
    private Long userId;
    private Long videoId;
    private Integer actionType;
}
