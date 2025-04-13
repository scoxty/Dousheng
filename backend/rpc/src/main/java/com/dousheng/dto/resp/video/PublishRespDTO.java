package com.dousheng.dto.resp.video;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PublishRespDTO implements Serializable {
    private String code;
    private String message;
}
