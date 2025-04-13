package com.dousheng.dto.req.video;

import com.dousheng.dto.common.VideoInfoDTO;
import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PublishReqDTO implements Serializable {
    private Long userId;
    private VideoInfoDTO videoInfo;
}
