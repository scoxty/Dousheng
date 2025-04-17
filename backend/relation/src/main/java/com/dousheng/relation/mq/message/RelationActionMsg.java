package com.dousheng.relation.mq.message;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RelationActionMsg implements Serializable {
    private Long fromUserId;
    private Long toUserId;
    private Integer actionType;
}
