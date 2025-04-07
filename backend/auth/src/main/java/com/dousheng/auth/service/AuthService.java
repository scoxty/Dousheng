package com.dousheng.auth.service;

import com.dousheng.dto.req.auth.GetSMSCodeReqDTO;
import com.dousheng.dto.req.auth.LoginReqDTO;
import com.dousheng.dto.req.auth.LogoutReqDTO;
import com.dousheng.dto.resp.auth.AuthenticateRespDTO;
import com.dousheng.dto.resp.auth.GetSMSCodeRespDTO;
import com.dousheng.dto.resp.auth.LoginRespDTO;
import com.dousheng.dto.resp.auth.LogoutRespDTO;

public interface AuthService {
    AuthenticateRespDTO authenticate(LoginReqDTO requestParam);
    GetSMSCodeRespDTO getSMSCode(GetSMSCodeReqDTO requestParam);
    LoginRespDTO login(LoginReqDTO requestParam);
    LogoutRespDTO logout(LogoutReqDTO requestParam);
}
