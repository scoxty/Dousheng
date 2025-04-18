package com.dousheng.dto.common;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CommentDTO implements Serializable {
    private Long id;
    private Long fatherCommentId;
    private Long authorId;
    private Long videoId;
    private Long userId;
    private String username;
    private String avatar;
    private String replayUsername;
    private String content;
    private Integer isLiked;
    private Integer likeCount;
    private Date createTime;
}
