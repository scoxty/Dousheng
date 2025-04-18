package com.dousheng.comment.service;

import com.dousheng.dto.req.comment.CommentActionReqDTO;
import com.dousheng.dto.req.comment.GetCommentListReqDTO;
import com.dousheng.dto.req.comment.GetVideoCommentCountsReqDTO;
import com.dousheng.dto.resp.comment.CommentActionRespDTO;
import com.dousheng.dto.resp.comment.GetCommentListRespDTO;
import com.dousheng.dto.resp.comment.GetVideoCommentCountsRespDTO;

public interface CommentService {
    CommentActionRespDTO commentAction(CommentActionReqDTO reqDTO);
    GetCommentListRespDTO getCommentList(GetCommentListReqDTO reqDTO);
    GetVideoCommentCountsRespDTO getVideoCommentCounts(GetVideoCommentCountsReqDTO requestParam);
}
