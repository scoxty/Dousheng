package com.dousheng.api.controller;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.dousheng.api.common.convention.result.PagedGridResult;
import com.dousheng.api.common.convention.result.Result;
import com.dousheng.api.common.convention.result.Results;
import com.dousheng.api.dto.resp.CommentDTO;
import com.dousheng.api.toolkit.PackerUtil;
import com.dousheng.dto.req.comment.CommentActionReqDTO;
import com.dousheng.dto.req.comment.GetCommentListReqDTO;
import com.dousheng.dto.req.comment.GetVideoCommentCountsReqDTO;
import com.dousheng.dto.resp.comment.CommentActionRespDTO;
import com.dousheng.dto.resp.comment.GetCommentListRespDTO;
import com.dousheng.dto.resp.comment.GetVideoCommentCountsRespDTO;
import com.dousheng.service.CommentRpcService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import static com.dousheng.api.common.convention.result.Result.SUCCESS_CODE;
import static com.dousheng.api.common.enums.CommentActionTypeEnum.CANCEL_COMMENT;
import static com.dousheng.api.common.enums.CommentActionTypeEnum.COMMENT;

@RestController
public class CommentController {

    private static final Log log = LogFactory.get();
    @DubboReference(timeout = 5000, retries = 3, loadbalance = "roundrobin")
    private CommentRpcService commentRpcService;

    @PostMapping("/comment/create")
    public Result<CommentDTO> createComment(@RequestBody CommentDTO commentDTO) {
        CommentActionReqDTO reqDTO = CommentActionReqDTO.builder()
                .fatherCommentId(NumberUtil.parseLong(commentDTO.getFatherCommentId()))
                .authorId(NumberUtil.parseLong(commentDTO.getVlogerId()))
                .videoId(NumberUtil.parseLong(commentDTO.getVlogId()))
                .userId(NumberUtil.parseLong(commentDTO.getCommentUserId()))
                .content(commentDTO.getContent())
                .actionType(COMMENT.type)
                .build();
        CommentActionRespDTO respDTO = commentRpcService.commentAction(reqDTO);
        if (respDTO.getCode().equals(SUCCESS_CODE)) {
            log.info("[createComment] success: req={}, resp={}", reqDTO, respDTO);
            return Results.success(PackerUtil.packApiCommentDTO(respDTO.getCommentDTO()));
        }
        Result<CommentDTO> result = new Result<>();
        result.setCode(respDTO.getCode());
        result.setMessage(respDTO.getMessage());
        log.error("[createComment] error: req={}, resp={}", reqDTO, respDTO);
        return result;
    }

    @DeleteMapping("/comment/delete")
    public Result<Void> deleteComment(@RequestParam String commentUserId,
                                      @RequestParam String commentId,
                                      @RequestParam String vlogId) {
        CommentActionReqDTO reqDTO = CommentActionReqDTO.builder()
                .commentId(NumberUtil.parseLong(commentId))
                .userId(NumberUtil.parseLong(commentUserId))
                .videoId(NumberUtil.parseLong(vlogId))
                .actionType(CANCEL_COMMENT.type)
                .build();
        CommentActionRespDTO respDTO = commentRpcService.commentAction(reqDTO);
        if (respDTO.getCode().equals(SUCCESS_CODE)) {
            log.info("[deleteComment] success: req={}, resp={}", reqDTO, respDTO);
            return Results.success();
        }
        log.info("[deleteComment] error: req={}, resp={}", reqDTO, respDTO);
        return Results.failure();
    }

    @GetMapping("/comment/list")
    public Result<PagedGridResult> getCommentList(@RequestParam String vlogId,
                                                  @RequestParam(defaultValue = "") String userId,
                                                  @RequestParam Integer page,
                                                  @RequestParam Integer pageSize) {
        GetCommentListReqDTO reqDTO = GetCommentListReqDTO.builder()
                .userId(StrUtil.isBlank(userId) ? null : NumberUtil.parseLong(userId))
                .videoId(NumberUtil.parseLong(vlogId))
                .page(page)
                .pageSize(pageSize)
                .build();
        GetCommentListRespDTO respDTO = commentRpcService.getCommentList(reqDTO);
        if (respDTO.getCode().equals(SUCCESS_CODE)) {
            PagedGridResult pagedGridResult = PagedGridResult.builder().
                    page(respDTO.getPage()).
                    total(respDTO.getTotalPage()).
                    records(respDTO.getTotalCount()).
                    rows(PackerUtil.packApiCommentDTOList(respDTO.getCommentList())).
                    build();
            log.info("[getCommentList] success: req={}, resp={}", reqDTO, respDTO);
           return Results.success(pagedGridResult);
        }
        Result<PagedGridResult> result = new Result<>();
        result.setCode(respDTO.getCode());
        result.setMessage(respDTO.getMessage());
        log.error("[getCommentList] error: req={}, resp={}", reqDTO, respDTO);
        return result;
    }

    @GetMapping("/comment/counts")
    public Result<Integer> getCommentCounts(@RequestParam String vlogId) {
        GetVideoCommentCountsReqDTO reqDTO = GetVideoCommentCountsReqDTO.builder()
               .videoId(NumberUtil.parseLong(vlogId))
               .build();
        GetVideoCommentCountsRespDTO respDTO = commentRpcService.getVideoCommentCounts(reqDTO);
        if (respDTO.getCode().equals(SUCCESS_CODE)) {
            log.info("[getCommentCounts] success: req={}, resp={}", reqDTO, respDTO);
            return Results.success(respDTO.getCommentCounts());
        }
        Result<Integer> result = new Result<>();
        result.setCode(respDTO.getCode());
        result.setMessage(respDTO.getMessage());
        log.error("[getCommentCounts] error: req={}, resp={}", reqDTO, respDTO);
        return result;
    }
}
