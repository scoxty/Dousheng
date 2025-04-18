package com.dousheng.dto.req.comment;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GetCommentListReqDTO implements Serializable {
    private Long videoId;
    private Long userId;
    private Integer page;
    private Integer pageSize;
}
