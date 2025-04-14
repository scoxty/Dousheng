package com.dousheng.video.handler;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.dousheng.dto.req.video.*;
import com.dousheng.dto.resp.video.*;
import com.dousheng.service.VideoRpcService;
import com.dousheng.video.common.convention.errorcode.BaseErrorCode;
import com.dousheng.video.common.convention.exception.AbstractException;
import com.dousheng.video.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Component;

@DubboService
@Component
@RequiredArgsConstructor
public class VideoServiceHandler implements VideoRpcService {

    private static final Log log = LogFactory.get();
    private final VideoService videoService;

    @Override
    public PublishRespDTO publish(PublishReqDTO requestParam) {
        PublishRespDTO respDTO = new PublishRespDTO();
        try {
            respDTO = videoService.publish(requestParam);
            log.info("[publish] success: req={}, resp={}", requestParam, respDTO);
        } catch (AbstractException e) {
            // 已知业务异常处理
            respDTO.setCode(e.getErrorCode());
            respDTO.setMessage(e.getErrorMessage());
            log.error("[publish] error: req={}, resp={}, err={}", requestParam, respDTO, e);
        } catch (Throwable e) {
            // 未知系统异常处理
            respDTO.setCode(BaseErrorCode.SERVICE_ERROR.code());
            respDTO.setMessage(BaseErrorCode.SERVICE_ERROR.message());
            log.error("[publish] error: req={}, resp={}, err={}", requestParam, respDTO, e);
        }
        return respDTO;
    }

    @Override
    public GetPublicListRespDTO getPublicList(GetPublicListReqDTO requestParam) {
        GetPublicListRespDTO respDTO = new GetPublicListRespDTO();
        try {
            respDTO = videoService.getPublicList(requestParam);
            log.info("[getPublicList] success: req={}, resp={}", requestParam, respDTO);
        } catch (AbstractException e) {
            // 已知业务异常处理
            respDTO.setCode(e.getErrorCode());
            respDTO.setMessage(e.getErrorMessage());
            log.error("[getPublicList] error: req={}, resp={}, err={}", requestParam, respDTO, e);
        } catch (Throwable e) {
            // 未知系统异常处理
            respDTO.setCode(BaseErrorCode.SERVICE_ERROR.code());
            respDTO.setMessage(BaseErrorCode.SERVICE_ERROR.message());
            log.error("[getPublicList] error: req={}, resp={}, err={}", requestParam, respDTO, e);
        }
        return respDTO;
    }

    @Override
    public GetVideoDetailRespDTO getVideoDetail(GetVideoDetailReqDTO requestParam) {
        GetVideoDetailRespDTO respDTO = new GetVideoDetailRespDTO();
        try {
            respDTO = videoService.getVideoDetail(requestParam);
            log.info("[getVideoDetail] success: req={}, resp={}", requestParam, respDTO);
        } catch (AbstractException e) {
            // 已知业务异常处理
            respDTO.setCode(e.getErrorCode());
            respDTO.setMessage(e.getErrorMessage());
            log.error("[getVideoDetail] error: req={}, resp={}, err={}", requestParam, respDTO, e);
        } catch (Throwable e) {
            // 未知系统异常处理
            respDTO.setCode(BaseErrorCode.SERVICE_ERROR.code());
            respDTO.setMessage(BaseErrorCode.SERVICE_ERROR.message());
            log.error("[getVideoDetail] error: req={}, resp={}, err={}", requestParam, respDTO, e);
        }
        return respDTO;
    }

    @Override
    public GetVideoDetailListRespDTO getVideoDetailList(GetVideoDetailListReqDTO requestParam) {
        GetVideoDetailListRespDTO respDTO = new GetVideoDetailListRespDTO();
        try {
            respDTO = videoService.getVideoDetailList(requestParam);
            log.info("[getVideoDetailList] success: req={}, resp={}", requestParam, respDTO);
        } catch (AbstractException e) {
            // 已知业务异常处理
            respDTO.setCode(e.getErrorCode());
            respDTO.setMessage(e.getErrorMessage());
            log.error("[getVideoDetailList] error: req={}, resp={}, err={}", requestParam, respDTO, e);
        } catch (Throwable e) {
            // 未知系统异常处理
            respDTO.setCode(BaseErrorCode.SERVICE_ERROR.code());
            respDTO.setMessage(BaseErrorCode.SERVICE_ERROR.message());
            log.error("[getVideoDetailList] error: req={}, resp={}, err={}", requestParam, respDTO, e);
        }
        return respDTO;
    }

    @Override
    public GetWorkCountRespDTO getWorkCount(GetWorkCountReqDTO requestParam) {
        GetWorkCountRespDTO respDTO = new GetWorkCountRespDTO();
        try {
            respDTO = videoService.getWorkCount(requestParam);
            log.info("[getWorkCount] success: req={}, resp={}", requestParam, respDTO);
        } catch (AbstractException e) {
            // 已知业务异常处理
            respDTO.setCode(e.getErrorCode());
            respDTO.setMessage(e.getErrorMessage());
            log.error("[getWorkCount] error: req={}, resp={}, err={}", requestParam, respDTO, e);
        } catch (Throwable e) {
            // 未知系统异常处理
            respDTO.setCode(BaseErrorCode.SERVICE_ERROR.code());
            respDTO.setMessage(BaseErrorCode.SERVICE_ERROR.message());
            log.error("[getWorkCount] error: req={}, resp={}, err={}", requestParam, respDTO, e);
        }
        return respDTO;
    }
}
