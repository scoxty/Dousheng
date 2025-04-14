package com.dousheng.api.controller;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.dousheng.api.common.biz.user.UserContext;
import com.dousheng.api.common.convention.exception.RemoteException;
import com.dousheng.api.common.convention.exception.ServiceException;
import com.dousheng.api.common.convention.result.PagedGridResult;
import com.dousheng.api.common.convention.result.Result;
import com.dousheng.api.common.convention.result.Results;
import com.dousheng.api.dto.req.PublishReqDTO;
import com.dousheng.api.dto.resp.IndexVideoDTO;
import com.dousheng.api.toolkit.PackerUtil;
import com.dousheng.dto.req.video.*;
import com.dousheng.dto.resp.video.*;
import com.dousheng.service.VideoRpcService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import static com.dousheng.api.common.constant.SuccessBaseRespConstant.SUCCESS_CODE;
import static com.dousheng.api.common.enums.YesOrNoEnum.NO;
import static com.dousheng.api.common.enums.YesOrNoEnum.YES;

@RestController
public class VideoController {
    private static final Log log = LogFactory.get();

    @DubboReference(timeout = 5000, retries = 3, loadbalance = "roundrobin")
    private VideoRpcService videoRpcService;

    @PostMapping("/vlog/publish")
    public Result<Void> publish(@RequestBody PublishReqDTO requestParam) {
        com.dousheng.dto.req.video.PublishReqDTO reqDTO = new com.dousheng.dto.req.video.PublishReqDTO();
        reqDTO.setUserId(UserContext.getUserId());
        reqDTO.setVideoInfo(PackerUtil.packRpcVideoInfo(requestParam));
        PublishRespDTO respDTO = videoRpcService.publish(reqDTO);
        if (respDTO.getCode().equals(SUCCESS_CODE)) {
            log.info("[publish] success: req={}, resp={}", requestParam, Results.success());
            return Results.success();
        }
        log.error("[publish] error: req={}, resp={}", requestParam, respDTO);
        return Results.failure(respDTO.getCode(), respDTO.getMessage());
    }

    @GetMapping("/vlog/detail")
    public Result<IndexVideoDTO> getVideoDetail(@RequestParam(defaultValue = "") String userId,
                                                @RequestParam String vlogId) {
        GetVideoDetailReqDTO reqDTO = GetVideoDetailReqDTO.builder().
                userId(userId.isEmpty() ? -1L : NumberUtil.parseLong(userId)).
                videoId(NumberUtil.parseLong(vlogId)).
                build();

        GetVideoDetailRespDTO respDTO = videoRpcService.getVideoDetail(reqDTO);
        if (respDTO.getCode().equals(SUCCESS_CODE)) {
            log.info("[getVideoDetail] success: req={}, resp={}", reqDTO, respDTO);
            return Results.success(PackerUtil.packApiVideoDetail(respDTO.getVideoInfo()));
        }
        Result<IndexVideoDTO> result = new Result<>();
        result.setCode(respDTO.getCode());
        result.setMessage(respDTO.getMessage());
        log.error("[getVideoDetail] error: req={}, resp={}", reqDTO, result);
        return result;
    }

    @PostMapping("/vlog/changeToPrivate")
    public Result<Void> changeToPrivate(@RequestParam String userId,
                                        @RequestParam String vlogId) {
        ChangeVideoToPrivateReqDTO reqDTO = ChangeVideoToPrivateReqDTO.builder().
                userId(NumberUtil.parseLong(userId)).
                videoId(NumberUtil.parseLong(vlogId)).
                build();
        ChangeVideoToPrivateRespDTO respDTO = videoRpcService.changeVideoToPrivate(reqDTO);
        if (!respDTO.getCode().equals(SUCCESS_CODE)) {
            log.error("[changeToPrivate] error: req={}, resp={}", reqDTO, respDTO);
            throw new RemoteException(respDTO.getMessage());
        }
        log.info("[changeToPrivate] success: req={}, resp={}", reqDTO, respDTO);
        return Results.success();
    }

    @PostMapping("/vlog/changeToPublic")
    public Result<Void> changeToPublic(@RequestParam String userId,
                                       @RequestParam String vlogId) {
        ChangeVideoToPublicReqDTO reqDTO = ChangeVideoToPublicReqDTO.builder().
                userId(NumberUtil.parseLong(userId)).
                videoId(NumberUtil.parseLong(vlogId)).
                build();
        ChangeVideoToPublicRespDTO respDTO = videoRpcService.changeVideoToPublic(reqDTO);
        if (!respDTO.getCode().equals(SUCCESS_CODE)) {
            log.error("[changeToPublic] error: req={}, resp={}", reqDTO, respDTO);
            throw new RemoteException(respDTO.getMessage());
        }
        log.info("[changeToPublic] success: req={}, resp={}", reqDTO, respDTO);
        return Results.success();
    }

    @GetMapping("/vlog/myPublicList")
    public Result<PagedGridResult> getMyPublicList(@RequestParam String userId,
                                                   @RequestParam Integer page,
                                                   @RequestParam Integer pageSize) {
        GetPublicListReqDTO reqDTO = GetPublicListReqDTO.builder().
                userId(NumberUtil.parseLong(userId)).
                page(page).
                pageSize(pageSize).
                isPrivate(NO.type).
                build();
        GetPublicListRespDTO respDTO = videoRpcService.getPublicList(reqDTO);
        if (respDTO.getCode().equals(SUCCESS_CODE)) {
            PagedGridResult pagedGridResult = PagedGridResult.builder().
                    page(respDTO.getPage()).
                    total(respDTO.getTotalPage()).
                    records(respDTO.getTotalCount()).
                    rows(PackerUtil.packApiPublishList(respDTO.getVideoList())).
                    build();
            log.info("[getMyPublicList] success: req={}, resp={}", reqDTO, Results.success(pagedGridResult));
            return Results.success(pagedGridResult);
        }

        Result<PagedGridResult> result = new Result<>();
        result.setCode(respDTO.getCode());
        result.setMessage(respDTO.getMessage());
        log.error("[getMyPublicList] error: req={}, resp={}", reqDTO, result);
        return result;
    }

    @GetMapping("/vlog/myPrivateList")
    public Result<PagedGridResult> getMyPrivateList(@RequestParam String userId,
                                                    @RequestParam Integer page,
                                                    @RequestParam Integer pageSize) {
        GetPrivateListReqDTO reqDTO = GetPrivateListReqDTO.builder().
                userId(NumberUtil.parseLong(userId)).
                page(page).
                pageSize(pageSize).
                isPrivate(YES.type).
                build();
        GetPrivateListRespDTO respDTO = videoRpcService.getPrivateList(reqDTO);
        if (!respDTO.getCode().equals(SUCCESS_CODE)) {
            log.error("[getMyPrivateList] error: req={}, resp={}", reqDTO, respDTO);
            throw new RemoteException(respDTO.getMessage());
        }
        PagedGridResult pagedGridResult = PagedGridResult.builder().
                page(respDTO.getPage()).
                total(respDTO.getTotalPage()).
                records(respDTO.getTotalCount()).
                rows(PackerUtil.packApiPublishList(respDTO.getVideoList())).
                build();
        log.info("[getMyPrivateList] success: req={}, resp={}", reqDTO, Results.success(pagedGridResult));
        return Results.success(pagedGridResult);
    }

}
