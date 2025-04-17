package com.dousheng.api.controller;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.dousheng.api.common.convention.result.PagedGridResult;
import com.dousheng.api.common.convention.result.Result;
import com.dousheng.api.common.convention.result.Results;
import com.dousheng.api.toolkit.PackerUtil;
import com.dousheng.dto.req.relation.FollowActionReqDTO;
import com.dousheng.dto.req.relation.GetFanListReqDTO;
import com.dousheng.dto.req.relation.GetFollowListReqDTO;
import com.dousheng.dto.req.relation.IsFollowReqDTO;
import com.dousheng.dto.resp.relation.FollowActionRespDTO;
import com.dousheng.dto.resp.relation.GetFanListRespDTO;
import com.dousheng.dto.resp.relation.GetFollowListRespDTO;
import com.dousheng.dto.resp.relation.IsFollowRespDTO;
import com.dousheng.service.RelationRpcService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.dousheng.api.common.convention.result.Result.SUCCESS_CODE;
import static com.dousheng.api.common.enums.RelationActionTypeEnum.FOLLOW;
import static com.dousheng.api.common.enums.RelationActionTypeEnum.UNFOLLOW;

@RestController
public class RelationController {

    private static final Log log = LogFactory.get();

    @DubboReference(timeout = 5000, retries = 3, loadbalance = "roundrobin")
    private RelationRpcService relationRpcService;

    @PostMapping("/fans/follow")
    public Result<Void> follow(@RequestParam String myId,
                               @RequestParam String vlogerId) {
        FollowActionReqDTO reqDTO = FollowActionReqDTO.builder().
                fromUserId(NumberUtil.parseLong(myId)).
                toUserId(NumberUtil.parseLong(vlogerId)).
                actionType(FOLLOW.type).
                build();
        FollowActionRespDTO respDTO = relationRpcService.followAction(reqDTO);
        if (respDTO.getCode().equals(SUCCESS_CODE)) {
            log.info("[follow] success: req={}, resp={}", reqDTO, respDTO);
            return Results.success();
        }
        log.error("[follow] error: req={}, resp={}", reqDTO, respDTO);
        return Results.failure();
    }

    @PostMapping("/fans/cancel")
    public Result<Void> unFollow(@RequestParam String myId,
                               @RequestParam String vlogerId) {
        FollowActionReqDTO reqDTO = FollowActionReqDTO.builder().
                fromUserId(NumberUtil.parseLong(myId)).
                toUserId(NumberUtil.parseLong(vlogerId)).
                actionType(UNFOLLOW.type).
                build();
        FollowActionRespDTO respDTO = relationRpcService.followAction(reqDTO);
        if (respDTO.getCode().equals(SUCCESS_CODE)) {
            log.info("[unFollow] success: req={}, resp={}", reqDTO, respDTO);
            return Results.success();
        }
        log.error("[unFollow] error: req={}, resp={}", reqDTO, respDTO);
        return Results.failure();
    }

    @GetMapping("/fans/queryDoIFollowVloger")
    public Result<Boolean> isFollowed(@RequestParam String myId,
                                    @RequestParam String vlogerId) {
        IsFollowReqDTO reqDTO = IsFollowReqDTO.builder().
                fromUserId(NumberUtil.parseLong(myId)).
                toUserId(NumberUtil.parseLong(vlogerId)).
                build();
        IsFollowRespDTO respDTO = relationRpcService.isFollow(reqDTO);
        if (respDTO.getCode().equals(SUCCESS_CODE)) {
            log.info("[isFollowed] success: req={}, resp={}", reqDTO, respDTO);
            return Results.success(respDTO.getIsFollow());
        }
        Result<Boolean> result = new Result<>();
        result.setCode(respDTO.getCode());
        result.setMessage(respDTO.getMessage());
        log.error("[isFollowed] error: req={}, resp={}", reqDTO, respDTO);
        return result;
    }

    @GetMapping("/fans/queryMyFollows")
    public Result<PagedGridResult> getMyFollows(@RequestParam String myId,
                                                @RequestParam Integer page,
                                                @RequestParam Integer pageSize) {
        GetFollowListReqDTO reqDTO = GetFollowListReqDTO.builder().
                userId(NumberUtil.parseLong(myId)).
                page(page).
                pageSize(pageSize).
                build();
        GetFollowListRespDTO respDTO = relationRpcService.getFollowList(reqDTO);
        if (respDTO.getCode().equals(SUCCESS_CODE)) {
            PagedGridResult pagedGridResult = PagedGridResult.builder().
                    page(respDTO.getPage()).
                    total(respDTO.getTotalPage()).
                    records(respDTO.getTotalCount()).
                    rows(PackerUtil.packRelationList(respDTO.getFollowList())).
                    build();
            log.info("[getMyFollows] success: req={}, resp={}", reqDTO, respDTO);
            return Results.success(pagedGridResult);
        }
        Result<PagedGridResult> result = new Result<>();
        result.setCode(respDTO.getCode());
        result.setMessage(respDTO.getMessage());
        log.error("[getMyFollows] error: req={}, resp={}", reqDTO, respDTO);
        return result;
    }

    @GetMapping("/fans/queryMyFans")
    public Result<PagedGridResult> getMyFans(@RequestParam String myId,
                                             @RequestParam Integer page,
                                             @RequestParam Integer pageSize) {
        GetFanListReqDTO reqDTO = GetFanListReqDTO.builder().
                userId(NumberUtil.parseLong(myId)).
                page(page).
                pageSize(pageSize).
                build();
        GetFanListRespDTO respDTO = relationRpcService.getFanList(reqDTO);
        if (respDTO.getCode().equals(SUCCESS_CODE)) {
            PagedGridResult pagedGridResult = PagedGridResult.builder().
                    page(respDTO.getPage()).
                    total(respDTO.getTotalPage()).
                    records(respDTO.getTotalCount()).
                    rows(PackerUtil.packRelationList(respDTO.getFanList())).
                    build();
            log.info("[getMyFans] success: req={}, resp={}", reqDTO, respDTO);
            return Results.success(pagedGridResult);
        }
        Result<PagedGridResult> result = new Result<>();
        result.setCode(respDTO.getCode());
        result.setMessage(respDTO.getMessage());
        log.error("[getMyFans] error: req={}, resp={}", reqDTO, respDTO);
        return result;
    }
}
