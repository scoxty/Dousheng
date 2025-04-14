package com.dousheng.dto.req.video;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ChangeVideoToPrivateReqDTO implements Serializable {
    private Long userId;
    private Long videoId;
}
