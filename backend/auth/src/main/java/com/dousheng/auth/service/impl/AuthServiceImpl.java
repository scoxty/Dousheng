package com.dousheng.auth.service.impl;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import cn.hutool.log.StaticLog;
import com.dousheng.auth.service.AuthService;
import com.dousheng.dto.req.auth.GetSMSCodeReqDTO;
import com.dousheng.dto.req.auth.LoginReqDTO;
import com.dousheng.dto.req.auth.LogoutReqDTO;
import com.dousheng.dto.resp.auth.AuthenticateRespDTO;
import com.dousheng.dto.resp.auth.GetSMSCodeRespDTO;
import com.dousheng.dto.resp.auth.LoginRespDTO;
import com.dousheng.dto.resp.auth.LogoutRespDTO;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private static final Log log = LogFactory.get();

    @Override
    public AuthenticateRespDTO authenticate(LoginReqDTO requestParam) {
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
