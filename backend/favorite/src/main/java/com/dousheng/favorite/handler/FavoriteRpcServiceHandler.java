package com.dousheng.favorite.handler;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.dousheng.dto.req.favorite.*;
import com.dousheng.dto.resp.favorite.*;
import com.dousheng.favorite.common.convention.errorcode.BaseErrorCode;
import com.dousheng.favorite.common.convention.exception.AbstractException;
import com.dousheng.favorite.service.FavoriteService;
import com.dousheng.service.FavoriteRpcService;
import lombok.RequiredArgsConstructor;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Component;

@DubboService
@Component
@RequiredArgsConstructor
public class FavoriteRpcServiceHandler implements FavoriteRpcService {

    private static final Log log = LogFactory.get();
    private final FavoriteService favoriteService;

    @Override
    public FavoriteActionRespDTO favoriteAction(FavoriteActionReqDTO requestParam) {
        FavoriteActionRespDTO respDTO = new FavoriteActionRespDTO();
        try {
            respDTO = favoriteService.favoriteAction(requestParam);
            log.info("[favoriteAction] success: req={}, resp={}", requestParam, respDTO);
        } catch (AbstractException e) {
            // 已知业务异常处理
            respDTO.setCode(e.getErrorCode());
            respDTO.setMessage(e.getErrorMessage());
            log.error("[favoriteAction] error: req={}, resp={}, err={}", requestParam, respDTO, e);
        }  catch (Throwable e) {
            // 未知系统异常处理
            respDTO.setCode(BaseErrorCode.SERVICE_ERROR.code());
            respDTO.setMessage(BaseErrorCode.SERVICE_ERROR.message());
            log.error("[favoriteAction] error: req={}, resp={}, err={}", requestParam, respDTO, e);
        }
        return respDTO;
    }

    @Override
    public GetFavoriteListRespDTO getFavoriteList(GetFavoriteListReqDTO requestParam) {
        GetFavoriteListRespDTO respDTO = new GetFavoriteListRespDTO();
        try {
            respDTO = favoriteService.getFavoriteList(requestParam);
            log.info("[getFavoriteList] success: req={}, resp={}", requestParam, respDTO);
        } catch (AbstractException e) {
            // 已知业务异常处理
            respDTO.setCode(e.getErrorCode());
            respDTO.setMessage(e.getErrorMessage());
            log.error("[getFavoriteList] error: req={}, resp={}, err={}", requestParam, respDTO, e);
        }  catch (Throwable e) {
            // 未知系统异常处理
            respDTO.setCode(BaseErrorCode.SERVICE_ERROR.code());
            respDTO.setMessage(BaseErrorCode.SERVICE_ERROR.message());
            log.error("[getFavoriteList] error: req={}, resp={}, err={}", requestParam, respDTO, e);
        }
        return respDTO;
    }

    @Override
    public GetTotalLikeMeCountRespDTO getTotalLikeMeCount(GetTotalLikeMeCountReqDTO requestParam) {
        GetTotalLikeMeCountRespDTO respDTO = new GetTotalLikeMeCountRespDTO();
        try {
            respDTO = favoriteService.getTotalLikeMeCount(requestParam);
            log.info("[getTotalLikeMeCount] success: req={}, resp={}", requestParam, respDTO);
        } catch (AbstractException e) {
            // 已知业务异常处理
            respDTO.setCode(e.getErrorCode());
            respDTO.setMessage(e.getErrorMessage());
            log.error("[getTotalLikeMeCount] error: req={}, resp={}, err={}", requestParam, respDTO, e);
        }  catch (Throwable e) {
            // 未知系统异常处理
            respDTO.setCode(BaseErrorCode.SERVICE_ERROR.code());
            respDTO.setMessage(BaseErrorCode.SERVICE_ERROR.message());
            log.error("[getTotalLikeMeCount] error: req={}, resp={}, err={}", requestParam, respDTO, e);
        }
        return respDTO;
    }

    @Override
    public GetVideoLikeCountsRespDTO getVideoLikeCounts(GetVideoLikeCountsReqDTO requestParam) {
        GetVideoLikeCountsRespDTO respDTO = new GetVideoLikeCountsRespDTO();
        try {
            respDTO = favoriteService.getVideoLikeCounts(requestParam);
            log.info("[getVideoLikeCounts] success: req={}, resp={}", requestParam, respDTO);
        } catch (AbstractException e) {
            // 已知业务异常处理
            respDTO.setCode(e.getErrorCode());
            respDTO.setMessage(e.getErrorMessage());
            log.error("[getVideoLikeCounts] error: req={}, resp={}, err={}", requestParam, respDTO, e);
        }  catch (Throwable e) {
            // 未知系统异常处理
            respDTO.setCode(BaseErrorCode.SERVICE_ERROR.code());
            respDTO.setMessage(BaseErrorCode.SERVICE_ERROR.message());
            log.error("[getVideoLikeCounts] error: req={}, resp={}, err={}", requestParam, respDTO, e);
        }
        return respDTO;
    }

    @Override
    public IsLikeVideoRespDTO isLikeVideo(IsLikeVideoReqDTO requestParam) {
        IsLikeVideoRespDTO respDTO = new IsLikeVideoRespDTO();
        try {
            respDTO = favoriteService.isLikeVideo(requestParam);
            log.info("[isLikeVideo] success: req={}, resp={}", requestParam, respDTO);
        } catch (AbstractException e) {
            // 已知业务异常处理
            respDTO.setCode(e.getErrorCode());
            respDTO.setMessage(e.getErrorMessage());
            log.error("[isLikeVideo] error: req={}, resp={}, err={}", requestParam, respDTO, e);
        }  catch (Throwable e) {
            // 未知系统异常处理
            respDTO.setCode(BaseErrorCode.SERVICE_ERROR.code());
            respDTO.setMessage(BaseErrorCode.SERVICE_ERROR.message());
            log.error("[isLikeVideo] error: req={}, resp={}, err={}", requestParam, respDTO, e);
        }
        return respDTO;
    }
}
