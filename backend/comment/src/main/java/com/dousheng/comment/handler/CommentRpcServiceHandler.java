package com.dousheng.comment.handler;

import com.dousheng.dto.req.comment.GetVideoCommentCountsReqDTO;
import com.dousheng.dto.resp.comment.GetVideoCommentCountsRespDTO;
import com.dousheng.service.CommentRpcService;
import org.apache.dubbo.config.annotation.DubboService;

@DubboService
public class CommentRpcServiceHandler implements CommentRpcService {
    @Override
    public GetVideoCommentCountsRespDTO getVideoCommentCounts(GetVideoCommentCountsReqDTO requestParam) {
        GetVideoCommentCountsRespDTO respDTO = GetVideoCommentCountsRespDTO.builder().code("0").message("success").commentCounts(0).build();
        return respDTO;
    }
}
