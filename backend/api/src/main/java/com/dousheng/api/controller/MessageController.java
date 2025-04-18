package com.dousheng.api.controller;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.dousheng.api.common.convention.result.PagedGridResult;
import com.dousheng.api.common.convention.result.Result;
import com.dousheng.api.common.convention.result.Results;
import com.dousheng.api.toolkit.PackerUtil;
import com.dousheng.dto.req.message.GetMessageListReqDTO;
import com.dousheng.dto.resp.comment.GetCommentListRespDTO;
import com.dousheng.dto.resp.message.GetMessageListRespDTO;
import com.dousheng.service.MessageRpcService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.dousheng.api.common.constant.SuccessBaseRespConstant.SUCCESS_CODE;

@RestController
public class MessageController {
    private static final Log log = LogFactory.get();

    @DubboReference(timeout = 5000, retries = 3, loadbalance = "roundrobin")
    private MessageRpcService messageRpcService;

    @GetMapping("/message/chat")
    public Result<PagedGridResult> getMessageList(@RequestParam String userId,
                                                  @RequestParam String friendId,
                                                  @RequestParam Integer page,
                                                  @RequestParam Integer pageSize) {
        GetMessageListReqDTO reqDTO = GetMessageListReqDTO.builder()
                .userId(NumberUtil.parseLong(userId))
                .friendId(NumberUtil.parseLong(friendId))
                .page(page)
                .pageSize(pageSize)
                .build();
        GetMessageListRespDTO respDTO = messageRpcService.getMessageList(reqDTO);
        if (respDTO.getCode().equals(SUCCESS_CODE)) {
            PagedGridResult pagedGridResult = PagedGridResult.builder().
                    page(respDTO.getPage()).
                    total(respDTO.getTotalPage()).
                    records(respDTO.getTotalCount()).
                    rows(PackerUtil.packApiMessageDTOList(respDTO.getMessageList())).
                    build();
            log.info("[getMessageList] success: req={}, resp={}", reqDTO, respDTO);
            return Results.success(pagedGridResult);
        }
        Result<PagedGridResult> result = new Result<>();
        result.setCode(respDTO.getCode());
        result.setMessage(respDTO.getMessage());
        log.error("[getMessageList] error: req={}, resp={}", reqDTO, result);
        return result;
    }
}
