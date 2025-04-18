package com.dousheng.service;

import com.dousheng.dto.req.comment.CommentActionReqDTO;
import com.dousheng.dto.req.comment.GetCommentListReqDTO;
import com.dousheng.dto.req.comment.GetVideoCommentCountsReqDTO;
import com.dousheng.dto.resp.comment.CommentActionRespDTO;
import com.dousheng.dto.resp.comment.GetCommentListRespDTO;
import com.dousheng.dto.resp.comment.GetVideoCommentCountsRespDTO;

public interface CommentRpcService {
    CommentActionRespDTO commentAction(CommentActionReqDTO requestParam);
    GetCommentListRespDTO getCommentList(GetCommentListReqDTO requestParam);
    GetVideoCommentCountsRespDTO getVideoCommentCounts(GetVideoCommentCountsReqDTO requestParam);
}
