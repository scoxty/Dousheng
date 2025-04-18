package com.dousheng.api.dto.resp;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MessageDTO {
    private String senderId;
    private String receiverId;
    private String content;
}
