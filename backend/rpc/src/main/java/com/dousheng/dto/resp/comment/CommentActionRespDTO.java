package com.dousheng.dto.resp.comment;

import com.dousheng.dto.common.CommentDTO;
import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CommentActionRespDTO implements Serializable {
    private String code;
    private String message;
    private CommentDTO commentDTO;
}
