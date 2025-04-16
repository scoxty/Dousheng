package com.dousheng.api.dto.req;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PublishReqDTO {
    private String vlogerId;
    private String url;
    private String cover;
    private String title;
    private Integer width;
    private Integer height;
}
