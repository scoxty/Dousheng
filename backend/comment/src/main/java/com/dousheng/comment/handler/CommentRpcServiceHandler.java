package com.dousheng.comment.handler;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.dousheng.comment.common.convention.errorcode.BaseErrorCode;
import com.dousheng.comment.common.convention.exception.AbstractException;
import com.dousheng.comment.service.CommentService;
import com.dousheng.dto.req.comment.CommentActionReqDTO;
import com.dousheng.dto.req.comment.GetCommentListReqDTO;
import com.dousheng.dto.req.comment.GetVideoCommentCountsReqDTO;
import com.dousheng.dto.resp.comment.CommentActionRespDTO;
import com.dousheng.dto.resp.comment.GetCommentListRespDTO;
import com.dousheng.dto.resp.comment.GetVideoCommentCountsRespDTO;
import com.dousheng.service.CommentRpcService;
import lombok.RequiredArgsConstructor;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Component;

@DubboService
@Component
@RequiredArgsConstructor
public class CommentRpcServiceHandler implements CommentRpcService {

    private static final Log log = LogFactory.get();

    private final CommentService commentService;

    @Override
    public CommentActionRespDTO commentAction(CommentActionReqDTO requestParam) {
        CommentActionRespDTO respDTO = new CommentActionRespDTO();
        try {
            respDTO = commentService.commentAction(requestParam);
            log.info("[commentAction] success: req={}, resp={}", requestParam, respDTO);
        } catch (AbstractException e) {
            // 已知业务异常处理
            respDTO.setCode(e.getErrorCode());
            respDTO.setMessage(e.getMessage());
            log.error("[commentAction] error: req={}, resp={}, err={}", requestParam, respDTO, e);
        } catch (Throwable e) {
            // 未知系统异常处理
            respDTO.setCode(BaseErrorCode.SERVICE_ERROR.code());
            respDTO.setMessage(BaseErrorCode.SERVICE_ERROR.message());
            log.error("[commentAction] error: req={}, resp={}, err={}", requestParam, respDTO, e);
        }
        return respDTO;
    }

    @Override
    public GetCommentListRespDTO getCommentList(GetCommentListReqDTO requestParam) {
        GetCommentListRespDTO respDTO = new GetCommentListRespDTO();
        try {
            respDTO = commentService.getCommentList(requestParam);
            log.info("[getCommentList] success: req={}, resp={}", requestParam, respDTO);
        } catch (AbstractException e) {
            // 已知业务异常处理
            respDTO.setCode(e.getErrorCode());
            respDTO.setMessage(e.getMessage());
            log.error("[getCommentList] error: req={}, resp={}, err={}", requestParam, respDTO, e);
        } catch (Throwable e) {
            // 未知系统异常处理
            respDTO.setCode(BaseErrorCode.SERVICE_ERROR.code());
            respDTO.setMessage(BaseErrorCode.SERVICE_ERROR.message());
            log.error("[getCommentList] error: req={}, resp={}, err={}", requestParam, respDTO, e);
        }
        return respDTO;
    }

    @Override
    public GetVideoCommentCountsRespDTO getVideoCommentCounts(GetVideoCommentCountsReqDTO requestParam) {
        GetVideoCommentCountsRespDTO respDTO = new GetVideoCommentCountsRespDTO();
        try {
            respDTO = commentService.getVideoCommentCounts(requestParam);
            log.info("[getVideoCommentCounts] success: req={}, resp={}", requestParam, respDTO);
        } catch (AbstractException e) {
            // 已知业务异常处理
            respDTO.setCode(e.getErrorCode());
            respDTO.setMessage(e.getMessage());
            log.error("[getVideoCommentCounts] error: req={}, resp={}, err={}", requestParam, respDTO, e);
        } catch (Throwable e) {
            // 未知系统异常处理
            respDTO.setCode(BaseErrorCode.SERVICE_ERROR.code());
            respDTO.setMessage(BaseErrorCode.SERVICE_ERROR.message());
            log.error("[getVideoCommentCounts] error: req={}, resp={}, err={}", requestParam, respDTO, e);
        }
        return respDTO;
    }
}
