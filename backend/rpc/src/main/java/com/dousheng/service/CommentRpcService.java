package com.dousheng.service;

import com.dousheng.dto.req.comment.GetVideoCommentCountsReqDTO;
import com.dousheng.dto.resp.comment.GetVideoCommentCountsRespDTO;

public interface CommentRpcService {
    GetVideoCommentCountsRespDTO getVideoCommentCounts(GetVideoCommentCountsReqDTO requestParam);
}
