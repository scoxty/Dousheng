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
public class GetPublicListRespDTO implements Serializable {
    private String code;
    private String message;
    private Integer page;			// 当前页数
    private Integer totalPage;		// 总页数
    private Integer totalCount;		// 总视频数
    private List<VideoInfoDTO> videoList;
}
