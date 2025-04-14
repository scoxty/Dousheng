package com.dousheng.dto.resp.video;

import com.dousheng.dto.common.VideoInfoDTO;
import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GetVideoDetailRespDTO implements Serializable {
    private String code;
    private String message;
    private VideoInfoDTO videoInfo;
}
