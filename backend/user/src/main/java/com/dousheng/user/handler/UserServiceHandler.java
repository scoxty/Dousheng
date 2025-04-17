package com.dousheng.user.handler;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.dousheng.dto.req.user.*;
import com.dousheng.dto.resp.user.*;
import com.dousheng.service.UserRpcService;
import com.dousheng.user.common.convention.errorcode.BaseErrorCode;
import com.dousheng.user.common.convention.exception.AbstractException;
import com.dousheng.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Component;

@DubboService
@Component
@RequiredArgsConstructor
public class UserServiceHandler implements UserRpcService {
    private static final Log log = LogFactory.get();

    private final UserService userService;

    @Override
    public GetUserInfoRespDTO getUserInfo(GetUserInfoReqDTO requestParam) {
        GetUserInfoRespDTO respDTO = new GetUserInfoRespDTO();
        try {
            respDTO = userService.getUserInfo(requestParam);
            log.info("[getUserInfo] success: req={}, resp={}", requestParam, respDTO);
        } catch (AbstractException e) {
            // 已知业务异常处理
            respDTO.setCode(e.getErrorCode());
            respDTO.setMessage(e.getErrorMessage());
            log.error("[getUserInfo] error: req={}, resp={}, err={}", requestParam, respDTO, e);
        } catch (Throwable e) {
            // 未知系统异常处理
            respDTO.setCode(BaseErrorCode.SERVICE_ERROR.code());
            respDTO.setMessage(BaseErrorCode.SERVICE_ERROR.message());
            log.error("[getUserInfo] error: req={}, resp={}, err={}", requestParam, respDTO, e);
        }
        return respDTO;
    }

    @Override
    public AddUserRespDTO addUser(AddUserReqDTO requestParam) {
        AddUserRespDTO respDTO = new AddUserRespDTO();
        try {
            respDTO = userService.addUser(requestParam);
            log.info("[addUser] success: req={}, resp={}", requestParam, respDTO);
        } catch (AbstractException e) {
            // 已知业务异常处理
            respDTO.setCode(e.getErrorCode());
            respDTO.setMessage(e.getErrorMessage());
            log.error("[addUser] error: req={}, resp={}, err={}", requestParam, respDTO, e);
        } catch (Throwable e) {
            // 未知系统异常处理
            respDTO.setCode(BaseErrorCode.SERVICE_ERROR.code());
            respDTO.setMessage(BaseErrorCode.SERVICE_ERROR.message());
            log.error("[addUser] error: req={}, resp={}, err={}", requestParam, respDTO, e);
        }
        return respDTO;
    }

    @Override
    public ModifyUserInfoRespDTO modifyUserInfo(ModifyUserInfoReqDTO requestParam) {
        ModifyUserInfoRespDTO respDTO = new ModifyUserInfoRespDTO();
        try {
            respDTO = userService.modifyUserInfo(requestParam);
            log.info("[modifyUserInfo] success: req={}, resp={}", requestParam, respDTO);
        } catch (AbstractException e) {
            // 已知业务异常处理
            respDTO.setCode(e.getErrorCode());
            respDTO.setMessage(e.getErrorMessage());
            log.error("[modifyUserInfo] error: req={}, resp={}, err={}", requestParam, respDTO, e);
        } catch (Throwable e) {
            // 未知系统异常处理
            respDTO.setCode(BaseErrorCode.SERVICE_ERROR.code());
            respDTO.setMessage(BaseErrorCode.SERVICE_ERROR.message());
            log.error("[modifyUserInfo] error: req={}, resp={}, err={}", requestParam, respDTO, e);
        }
        return respDTO;
    }

    @Override
    public ModifyImageRespDTO modifyImage(ModifyImageReqDTO requestParam) {
        ModifyImageRespDTO respDTO = new ModifyImageRespDTO();
        try {
            respDTO = userService.modifyImage(requestParam);
            log.info("[modifyImage] success: req={}, resp={}", requestParam, respDTO);
        } catch (AbstractException e) {
            // 已知业务异常处理
            respDTO.setCode(e.getErrorCode());
            respDTO.setMessage(e.getErrorMessage());
            log.error("[modifyImage] error: req={}, resp={}, err={}", requestParam, respDTO, e);
        } catch (Throwable e) {
            // 未知系统异常处理
            respDTO.setCode(BaseErrorCode.SERVICE_ERROR.code());
            respDTO.setMessage(BaseErrorCode.SERVICE_ERROR.message());
            log.error("[modifyImage] error: req={}, resp={}, err={}", requestParam, respDTO, e);
        }
        return respDTO;
    }

    @Override
    public IsExistRespDTO isExist(IsExistReqDTO requestParam) {
        IsExistRespDTO respDTO = new IsExistRespDTO();
        try {
            respDTO = userService.isExist(requestParam);
            log.info("[isExist] success: req={}, resp={}", requestParam, respDTO);
        } catch (AbstractException e) {
            // 已知业务异常处理
            respDTO.setCode(e.getErrorCode());
            respDTO.setMessage(e.getErrorMessage());
            log.error("[isExist] error: req={}, resp={}, err={}", requestParam, respDTO, e);
        } catch (Throwable e) {
            // 未知系统异常处理
            respDTO.setCode(BaseErrorCode.SERVICE_ERROR.code());
            respDTO.setMessage(BaseErrorCode.SERVICE_ERROR.message());
            log.error("[isExist] error: req={}, resp={}, err={}", requestParam, respDTO, e);
        }
        return respDTO;
    }

    @Override
    public GetBaseUserInfoListRespDTO getBaseUserInfoList(GetBaseUserInfoListReqDTO requestParam) {
        GetBaseUserInfoListRespDTO respDTO = new GetBaseUserInfoListRespDTO();
        try {
            respDTO = userService.getBaseUserInfoList(requestParam);
            log.info("[getBaseUserInfoList] success: req={}, resp={}", requestParam, respDTO);
        } catch (AbstractException e) {
            // 已知业务异常处理
            respDTO.setCode(e.getErrorCode());
            respDTO.setMessage(e.getErrorMessage());
            log.error("[getBaseUserInfoList] error: req={}, resp={}, err={}", requestParam, respDTO, e);
        } catch (Throwable e) {
            // 未知系统异常处理
            respDTO.setCode(BaseErrorCode.SERVICE_ERROR.code());
            respDTO.setMessage(BaseErrorCode.SERVICE_ERROR.message());
            log.error("[getBaseUserInfoList] error: req={}, resp={}, err={}", requestParam, respDTO, e);
        }
        return respDTO;
    }
}
