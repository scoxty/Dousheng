package com.dousheng.dto.resp.comment;

import com.dousheng.dto.common.CommentDTO;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GetCommentListRespDTO implements Serializable {
    private String code;
    private String message;
    private Integer page;			// 当前页数
    private Integer totalPage;		// 总页数
    private Integer totalCount;		// 总视频数
    private List<CommentDTO> commentList;
}
