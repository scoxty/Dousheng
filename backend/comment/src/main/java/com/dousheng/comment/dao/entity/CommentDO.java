package com.dousheng.comment.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.dousheng.comment.common.database.BaseDO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "comment")
public class CommentDO extends BaseDO {
    private Long id;
    private Long fatherCommentId;
    private Long videoId;
    private Long authorId;
    private Long userId;
    private String content;
}
