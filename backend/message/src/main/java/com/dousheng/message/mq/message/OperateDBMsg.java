package com.dousheng.message.mq.message;


import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OperateDBMsg implements Serializable {
    private Long senderId;
    private Long receiverId;
    private String content;
}
