package com.dousheng.video.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "video")
public class VideoDO {
    private Long id;
    private Long authorId;
    private String playUrl;
    private String coverUrl;
    private String title;
    private Integer width;
    private Integer height;
    private Integer isPrivate;
    private Date createTime;
    private Date updateTime;
}
