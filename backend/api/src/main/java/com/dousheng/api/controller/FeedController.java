package com.dousheng.api.controller;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.dousheng.api.common.convention.result.PagedGridResult;
import com.dousheng.api.common.convention.result.Result;
import com.dousheng.api.common.convention.result.Results;
import com.dousheng.api.toolkit.PackerUtil;
import com.dousheng.dto.req.feed.GetIndexFeedReqDTO;
import com.dousheng.dto.resp.feed.GetIndexFeedRespDTO;
import com.dousheng.service.FeedRpcService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.dousheng.api.common.constant.SuccessBaseRespConstant.SUCCESS_CODE;

@RestController
public class FeedController {
    private static final Log log = LogFactory.get();

    @DubboReference(timeout = 5000, retries = 3, loadbalance = "roundrobin")
    private FeedRpcService feedRpcService;

    @GetMapping("/vlog/indexList")
    public Result<PagedGridResult> getIndexList(@RequestParam(defaultValue = "") String userId,
                                                @RequestParam(defaultValue = "") String search,
                                                @RequestParam Integer page,
                                                @RequestParam Integer pageSize) {
        GetIndexFeedReqDTO reqDTO = GetIndexFeedReqDTO.builder().
                userId(NumberUtil.parseLong(userId)).
                page(page).
                pageSize(pageSize).
                build();
        GetIndexFeedRespDTO respDTO = feedRpcService.getIndexFeed(reqDTO);
        if (respDTO.getCode().equals(SUCCESS_CODE)) {
            PagedGridResult pagedGridResult = PagedGridResult.builder().
                    page(respDTO.getPage()).
                    total(respDTO.getTotalPage()).
                    records(respDTO.getTotalCount()).
                    rows(PackerUtil.packApiIndexFeed(respDTO.getVideoList())).
                    build();
            log.info("[getIndexList] success: req={}, resp={}", reqDTO, Results.success(pagedGridResult));
            return Results.success(pagedGridResult);
        }

        Result<PagedGridResult> result = new Result<>();
        result.setCode(respDTO.getCode());
        result.setMessage(respDTO.getMessage());
        log.error("[getIndexList] error: req={}, resp={}", reqDTO, result);
        return result;
    }
}
