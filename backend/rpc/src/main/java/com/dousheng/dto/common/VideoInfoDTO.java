package com.dousheng.dto.common;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class VideoInfoDTO implements Serializable {
    private Long id;
    private Long authorId;
    private String authorName;
    private String authorAvatar;
    private String playUrl;
    private String coverUrl;
    private String title;
    private Integer width;
    private Integer height;
    private Integer likeCounts;
    private Integer commentCounts;
    private Integer isPrivate;
    private Boolean isFollow;
    private Boolean isFavorite;
    private Date createTime;
    private Date updateTime;
}
