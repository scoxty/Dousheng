package com.dousheng.dto.req.comment;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CommentActionReqDTO implements Serializable {
    private Long commentId;
    private Long authorId;
    private Long videoId;
    private Long fatherCommentId;
    private Long userId;
    private String content;
    private Integer actionType;
}
