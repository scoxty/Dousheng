package com.dousheng.api.controller;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.dousheng.api.common.biz.user.UserContext;
import com.dousheng.api.common.convention.result.PagedGridResult;
import com.dousheng.api.common.convention.result.Result;
import com.dousheng.api.common.convention.result.Results;
import com.dousheng.api.toolkit.PackerUtil;
import com.dousheng.dto.req.favorite.FavoriteActionReqDTO;
import com.dousheng.dto.req.favorite.GetFavoriteListReqDTO;
import com.dousheng.dto.req.favorite.GetVideoLikeCountsReqDTO;
import com.dousheng.dto.req.favorite.IsLikeVideoReqDTO;
import com.dousheng.dto.resp.favorite.FavoriteActionRespDTO;
import com.dousheng.dto.resp.favorite.GetFavoriteListRespDTO;
import com.dousheng.dto.resp.favorite.GetVideoLikeCountsRespDTO;
import com.dousheng.dto.resp.favorite.IsLikeVideoRespDTO;
import com.dousheng.service.FavoriteRpcService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.dousheng.api.common.constant.SuccessBaseRespConstant.SUCCESS_CODE;
import static com.dousheng.api.common.enums.FavoriteActionTypeEnum.DISLIKE;
import static com.dousheng.api.common.enums.FavoriteActionTypeEnum.LIKE;

@RestController
public class FavoriteController {

    private static final Log log = LogFactory.get();
    @DubboReference
    private FavoriteRpcService favoriteRpcService;

    @PostMapping("/vlog/like")
    public Result<Void> like(@RequestParam String userId,
                             @RequestParam String vlogerId,
                             @RequestParam String vlogId) {
        FavoriteActionReqDTO reqDTO = FavoriteActionReqDTO.builder()
                .userId(NumberUtil.parseLong(userId)).
                authorId(NumberUtil.parseLong(vlogerId)).
                videoId(NumberUtil.parseLong(vlogId)).
                actionType(LIKE.type).
                build();
        FavoriteActionRespDTO respDTO = favoriteRpcService.favoriteAction(reqDTO);
        if (!respDTO.getCode().equals(SUCCESS_CODE)) {
            log.error("[like] error: req={}, resp={}", reqDTO, respDTO);
            return Results.failure();
        }
        log.info("[like] success: req={}, resp={}", reqDTO, respDTO);
        return Results.success();
    }

    @PostMapping("/vlog/unlike")
    public Result<Void> unlike(@RequestParam String userId,
                             @RequestParam String vlogerId,
                             @RequestParam String vlogId) {
        FavoriteActionReqDTO reqDTO = FavoriteActionReqDTO.builder()
                .userId(NumberUtil.parseLong(userId)).
                authorId(NumberUtil.parseLong(vlogerId)).
                videoId(NumberUtil.parseLong(vlogId)).
                actionType(DISLIKE.type).
                build();
        FavoriteActionRespDTO respDTO = favoriteRpcService.favoriteAction(reqDTO);
        if (!respDTO.getCode().equals(SUCCESS_CODE)) {
            log.error("[unlike] error: req={}, resp={}", reqDTO, respDTO);
            return Results.failure();
        }
        log.info("[unlike] success: req={}, resp={}", reqDTO, respDTO);
        return Results.success();
    }

    @GetMapping("/vlog/myLikedList")
    public Result<PagedGridResult> getMyLikedList(@RequestParam String userId,
                                                  @RequestParam Integer page,
                                                  @RequestParam Integer pageSize) {
        GetFavoriteListReqDTO reqDTO = GetFavoriteListReqDTO.builder().
                page(page).
                pageSize(pageSize).
                userId(NumberUtil.parseLong(userId)).
                build();
        GetFavoriteListRespDTO respDTO = favoriteRpcService.getFavoriteList(reqDTO);
        if (respDTO.getCode().equals(SUCCESS_CODE)) {
            PagedGridResult pagedGridResult = PagedGridResult.builder().
                    page(respDTO.getPage()).
                    total(respDTO.getTotalPage()).
                    records(respDTO.getTotalCount()).
                    rows(PackerUtil.packApiPublishList(respDTO.getVideoList())). // 兼容前端...
                    build();
            log.info("[getMyLikedList] error: req={}, resp={}", reqDTO, respDTO);
            return Results.success(pagedGridResult);
        }
        Result<PagedGridResult> result = new Result<>();
        result.setCode(respDTO.getCode());
        result.setMessage(respDTO.getMessage());
        log.error("[getMyLikedList] error: req={}, resp={}", reqDTO, result);
        return result;
    }

    @GetMapping("/vlog/totalLikedCounts")
    public Result<Integer> getVideoLikedCounts(@RequestParam String vlogId) {
        GetVideoLikeCountsReqDTO reqDTO = GetVideoLikeCountsReqDTO.builder().
                videoId(NumberUtil.parseLong(vlogId)).
                build();
        GetVideoLikeCountsRespDTO respDTO = favoriteRpcService.getVideoLikeCounts(reqDTO);
        if (respDTO.getCode().equals(SUCCESS_CODE)) {
            log.info("[getVideoLikedCounts] success: req={}, resp={}", reqDTO, respDTO);
            return Results.success(respDTO.getLikeCounts());
        }
        Result<Integer> result = new Result<>();
        result.setCode(respDTO.getCode());
        result.setMessage(respDTO.getMessage());
        log.error("[getVideoLikedCounts] error: req={}, resp={}", reqDTO, result);
        return result;
    }

    @GetMapping("/vlog/queryDoILikeVideo")
    public Result<Boolean> isLiked(@RequestParam String vlogId) {
        IsLikeVideoReqDTO reqDTO = IsLikeVideoReqDTO.builder().
                userId(UserContext.getUserId()).
                videoId(NumberUtil.parseLong(vlogId)).
                build();
        IsLikeVideoRespDTO respDTO = favoriteRpcService.isLikeVideo(reqDTO);
        if (respDTO.getCode().equals(SUCCESS_CODE)) {
            log.info("[isLiked] success: req={}, resp={}", reqDTO, respDTO);
            return Results.success(respDTO.getIsLike());
        }
        Result<Boolean> result = new Result<>();
        result.setCode(respDTO.getCode());
        result.setMessage(respDTO.getMessage());
        log.error("[isLiked] error: req={}, resp={}", reqDTO, result);
        return result;
    }
}
