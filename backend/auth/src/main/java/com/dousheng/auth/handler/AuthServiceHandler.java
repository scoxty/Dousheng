package com.dousheng.auth.handler;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.dousheng.auth.service.AuthService;
import com.dousheng.dto.req.auth.AuthenticateReqDTO;
import com.dousheng.dto.req.auth.GetSMSCodeReqDTO;
import com.dousheng.dto.req.auth.LoginReqDTO;
import com.dousheng.dto.req.auth.LogoutReqDTO;
import com.dousheng.dto.resp.auth.AuthenticateRespDTO;
import com.dousheng.dto.resp.auth.GetSMSCodeRespDTO;
import com.dousheng.dto.resp.auth.LoginRespDTO;
import com.dousheng.dto.resp.auth.LogoutRespDTO;
import com.dousheng.service.AuthRpcService;
import lombok.RequiredArgsConstructor;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@DubboService
@Component
@RequiredArgsConstructor
public class AuthServiceHandler implements AuthRpcService {
    private static final Log log = LogFactory.get();

    private final AuthService authService;

    @Override
    public AuthenticateRespDTO authenticate(AuthenticateReqDTO requestParam) {
        return null;
    }

    @Override
    public GetSMSCodeRespDTO getSMSCode(GetSMSCodeReqDTO requestParam) {
        return null;
    }

    @Override
    public LoginRespDTO login(LoginReqDTO requestParam) {
        return null;
    }

    @Override
    public LogoutRespDTO logout(LogoutReqDTO requestParam) {
        return null;
    }
}
