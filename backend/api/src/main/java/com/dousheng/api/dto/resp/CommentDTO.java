package com.dousheng.api.dto.resp;

import lombok.*;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CommentDTO {
    private String id;
    private String vlogerId;
    private String fatherCommentId;
    private String vlogId;
    private String commentUserId;
    private String commentUserNickname;
    private String commentUserFace;
    private String content;
    private Integer likeCounts;
    private String replyedUserNickname;
    private Date createTime;
    private Integer isLike = 0;
}
