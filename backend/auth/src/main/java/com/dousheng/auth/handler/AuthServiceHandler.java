package com.dousheng.auth.handler;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.dousheng.auth.common.convention.errorcode.BaseErrorCode;
import com.dousheng.auth.common.convention.exception.AbstractException;
import com.dousheng.auth.service.AuthService;
import com.dousheng.dto.req.auth.AuthenticateReqDTO;
import com.dousheng.dto.req.auth.RegisterReqDTO;
import com.dousheng.dto.req.auth.LoginReqDTO;
import com.dousheng.dto.req.auth.LogoutReqDTO;
import com.dousheng.dto.resp.auth.AuthenticateRespDTO;
import com.dousheng.dto.resp.auth.RegisterRespDTO;
import com.dousheng.dto.resp.auth.LoginRespDTO;
import com.dousheng.dto.resp.auth.LogoutRespDTO;
import com.dousheng.service.AuthRpcService;
import lombok.RequiredArgsConstructor;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Component;

@DubboService
@Component
@RequiredArgsConstructor
public class AuthServiceHandler implements AuthRpcService {
    private static final Log log = LogFactory.get();

    private final AuthService authService;

    @Override
    public AuthenticateRespDTO authenticate(AuthenticateReqDTO requestParam) {
        AuthenticateRespDTO respDTO = new AuthenticateRespDTO();
        try {
            respDTO = authService.authenticate(requestParam);
            log.info("[authenticate] success: req={}, resp={}", requestParam, respDTO);
        } catch (AbstractException e) {
            // 已知业务异常处理
            respDTO.setCode(e.getErrorCode());
            respDTO.setMessage(e.getMessage());
            log.error("[authenticate] error: req={}, resp={}, err={}", requestParam, respDTO, e);
        } catch (Throwable e) {
            // 未知系统异常处理
            respDTO.setCode(BaseErrorCode.SERVICE_ERROR.code());
            respDTO.setMessage(BaseErrorCode.SERVICE_ERROR.message());
            log.error("[authenticate] error: req={}, resp={}, err={}", requestParam, respDTO, e);
        }
        return respDTO;
    }

    @Override
    public RegisterRespDTO register(RegisterReqDTO requestParam) {
        RegisterRespDTO respDTO = new RegisterRespDTO();
        try {
            respDTO = authService.register(requestParam);
            log.info("[register] success: req={}, resp={}", requestParam, respDTO);
        } catch (AbstractException e) {
            // 已知业务异常处理
            respDTO.setCode(e.getErrorCode());
            respDTO.setMessage(e.getMessage());
            log.error("[register] error: req={}, resp={}, err={}", requestParam, respDTO, e);
        } catch (Throwable e) {
            // 未知系统异常处理
            respDTO.setCode(BaseErrorCode.SERVICE_ERROR.code());
            respDTO.setMessage(BaseErrorCode.SERVICE_ERROR.message());
            log.error("[register] error: req={}, resp={}, err={}", requestParam, respDTO, e);
        }
        return respDTO;
    }

    @Override
    public LoginRespDTO login(LoginReqDTO requestParam) {
        LoginRespDTO respDTO = new LoginRespDTO();
        try {
            respDTO = authService.login(requestParam);
            log.info("[login] success: req={}, resp={}", requestParam, respDTO);
        } catch (AbstractException e) {
            respDTO.setCode(e.getErrorCode());
            respDTO.setMessage(e.getMessage());
            log.error("[login] error: req={}, resp={}, err={}", requestParam, respDTO, e);
        } catch (Throwable e) {
            respDTO.setCode(BaseErrorCode.SERVICE_ERROR.code());
            respDTO.setMessage(BaseErrorCode.SERVICE_ERROR.message());
            log.error("[login] error: req={}, resp={}, err={}", requestParam, respDTO, e);
        }
        return respDTO;
    }

    @Override
    public LogoutRespDTO logout(LogoutReqDTO requestParam) {
        LogoutRespDTO respDTO = new LogoutRespDTO();
        try {
            respDTO = authService.logout(requestParam);
            log.info("[logout] success: req={}, resp={}", requestParam, respDTO);
        } catch (AbstractException e) {
            respDTO.setCode(e.getErrorCode());
            respDTO.setMessage(e.getMessage());
            log.error("[logout] error: req={}, resp={}, err={}", requestParam, respDTO, e);
        } catch (Throwable e) {
            respDTO.setCode(BaseErrorCode.SERVICE_ERROR.code());
            respDTO.setMessage(BaseErrorCode.SERVICE_ERROR.message());
            log.error("[logout] error: req={}, resp={}, err={}", requestParam, respDTO, e);
        }
        return respDTO;
    }
}
