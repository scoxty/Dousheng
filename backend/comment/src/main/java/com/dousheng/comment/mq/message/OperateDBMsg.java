package com.dousheng.comment.mq.message;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OperateDBMsg implements Serializable {
    private Long id; // 评论id
    private Long fatherCommentId;
    private Long videoId;
    private Long authorId;
    private Long userId;
    private String content;
    private Integer actionType;
}
