package com.dousheng.api.dto.resp;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class VideoInfoDTO {
    private String id;
    private String vlogerId;
    private String url;
    private String cover;
    private String title;
    private Integer width;
    private Integer height;
    private Integer likeCounts;
    private Integer commentsCounts;
}
