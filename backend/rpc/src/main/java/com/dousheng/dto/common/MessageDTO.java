package com.dousheng.dto.common;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MessageDTO implements Serializable {
    private Long id;
    private Long senderId;
    private Long receiverId;
    private String content;
}
