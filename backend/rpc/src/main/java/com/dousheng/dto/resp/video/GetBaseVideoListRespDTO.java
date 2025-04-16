package com.dousheng.dto.resp.video;

import com.dousheng.dto.common.VideoInfoDTO;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GetBaseVideoListRespDTO implements Serializable {
    private String code;
    private String message;
    private List<VideoInfoDTO> videoList;
}
