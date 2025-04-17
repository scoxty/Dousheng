package com.dousheng.relation.handler;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.dousheng.dto.req.relation.*;
import com.dousheng.dto.resp.relation.*;
import com.dousheng.relation.common.convention.errorcode.BaseErrorCode;
import com.dousheng.relation.common.convention.exception.AbstractException;
import com.dousheng.relation.service.RelationService;
import com.dousheng.service.RelationRpcService;
import lombok.RequiredArgsConstructor;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Component;

@DubboService
@Component
@RequiredArgsConstructor
public class RelationRpcServiceHandler implements RelationRpcService {

    private static final Log log = LogFactory.get();
    private final RelationService relationService;

    @Override
    public FollowActionRespDTO followAction(FollowActionReqDTO requestParam) {
        FollowActionRespDTO respDTO = new FollowActionRespDTO();
        try {
            respDTO = relationService.followAction(requestParam);
            log.info("[followAction] success: req={}, resp={}", requestParam, respDTO);
        } catch (AbstractException e) {
            // 已知业务异常处理
            respDTO.setCode(e.getErrorCode());
            respDTO.setMessage(e.getErrorMessage());
            log.error("[followAction] error: req={}, resp={}, err={}", requestParam, respDTO, e);
        } catch (Throwable e) {
            // 未知系统异常处理
            respDTO.setCode(BaseErrorCode.SERVICE_ERROR.code());
            respDTO.setMessage(BaseErrorCode.SERVICE_ERROR.message());
            log.error("[followAction] error: req={}, resp={}, err={}", requestParam, respDTO, e);
        }
        return respDTO;
    }

    @Override
    public IsFollowRespDTO isFollow(IsFollowReqDTO requestParam) {
        IsFollowRespDTO respDTO = new IsFollowRespDTO();
        try {
            respDTO = relationService.isFollow(requestParam);
            log.info("[isFollow] success: req={}, resp={}", requestParam, respDTO);
        } catch (AbstractException e) {
            // 已知业务异常处理
            respDTO.setCode(e.getErrorCode());
            respDTO.setMessage(e.getErrorMessage());
            log.error("[isFollow] error: req={}, resp={}", requestParam, respDTO, e);
        } catch (Throwable e) {
            // 未知系统异常处理
            respDTO.setCode(BaseErrorCode.SERVICE_ERROR.code());
            respDTO.setMessage(BaseErrorCode.SERVICE_ERROR.message());
            log.error("[isFollow] error: req={}, resp={}", requestParam, respDTO, e);
        }
        return respDTO;
    }

    @Override
    public GetFollowListRespDTO getFollowList(GetFollowListReqDTO requestParam) {
        GetFollowListRespDTO respDTO = new GetFollowListRespDTO();
        try {
            respDTO = relationService.getFollowList(requestParam);
            log.info("[getFollowList] success: req={}, resp={}", requestParam, respDTO);
        } catch (AbstractException e) {
            // 已知业务异常处理
            respDTO.setCode(e.getErrorCode());
            respDTO.setMessage(e.getErrorMessage());
            log.error("[getFollowList] error: req={}, resp={}", requestParam, respDTO, e);
        } catch (Throwable e) {
            // 未知系统异常处理
            respDTO.setCode(BaseErrorCode.SERVICE_ERROR.code());
            respDTO.setMessage(BaseErrorCode.SERVICE_ERROR.message());
            log.error("[getFollowList] error: req={}, resp={}", requestParam, respDTO, e);
        }
        return respDTO;
    }

    @Override
    public GetFollowsCountRespDTO getFollowsCount(GetFollowsCountReqDTO requestParam) {
        GetFollowsCountRespDTO respDTO = new GetFollowsCountRespDTO();
        try {
            respDTO = relationService.getFollowsCount(requestParam);
            log.info("[getFollowsCount] success: req={}, resp={}", requestParam, respDTO);
        } catch (AbstractException e) {
            // 已知业务异常处理
            respDTO.setCode(e.getErrorCode());
            respDTO.setMessage(e.getErrorMessage());
            log.error("[getFollowsCount] error: req={}, resp={}", requestParam, respDTO, e);
        } catch (Throwable e) {
            // 未知系统异常处理
            respDTO.setCode(BaseErrorCode.SERVICE_ERROR.code());
            respDTO.setMessage(BaseErrorCode.SERVICE_ERROR.message());
            log.error("[getFollowsCount] error: req={}, resp={}", requestParam, respDTO, e);
        }
        return respDTO;
    }

    @Override
    public GetFanListRespDTO getFanList(GetFanListReqDTO requestParam) {
        GetFanListRespDTO respDTO = new GetFanListRespDTO();
        try {
            respDTO = relationService.getFanList(requestParam);
            log.info("[getFanList] success: req={}, resp={}", requestParam, respDTO);
        } catch (AbstractException e) {
            // 已知业务异常处理
            respDTO.setCode(e.getErrorCode());
            respDTO.setMessage(e.getErrorMessage());
            log.error("[getFanList] error: req={}, resp={}", requestParam, respDTO, e);
        } catch (Throwable e) {
            // 未知系统异常处理
            respDTO.setCode(BaseErrorCode.SERVICE_ERROR.code());
            respDTO.setMessage(BaseErrorCode.SERVICE_ERROR.message());
            log.error("[getFanList] error: req={}, resp={}", requestParam, respDTO, e);
        }
        return respDTO;
    }

    @Override
    public GetFansCountRespDTO getFansCount(GetFansCountReqDTO requestParam) {
        GetFansCountRespDTO respDTO = new GetFansCountRespDTO();
        try {
            respDTO = relationService.getFansCount(requestParam);
            log.info("[getFansCount] success: req={}, resp={}", requestParam, respDTO);
        } catch (AbstractException e) {
            // 已知业务异常处理
            respDTO.setCode(e.getErrorCode());
            respDTO.setMessage(e.getErrorMessage());
            log.error("[getFansCount] error: req={}, resp={}", requestParam, respDTO, e);
        } catch (Throwable e) {
            // 未知系统异常处理
            respDTO.setCode(BaseErrorCode.SERVICE_ERROR.code());
            respDTO.setMessage(BaseErrorCode.SERVICE_ERROR.message());
            log.error("[getFansCount] error: req={}, resp={}", requestParam, respDTO, e);
        }
        return respDTO;
    }

}
