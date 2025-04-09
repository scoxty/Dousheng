package com.dousheng.auth.service;

import com.dousheng.dto.req.auth.AuthenticateReqDTO;
import com.dousheng.dto.req.auth.RegisterReqDTO;
import com.dousheng.dto.req.auth.LoginReqDTO;
import com.dousheng.dto.req.auth.LogoutReqDTO;
import com.dousheng.dto.resp.auth.AuthenticateRespDTO;
import com.dousheng.dto.resp.auth.RegisterRespDTO;
import com.dousheng.dto.resp.auth.LoginRespDTO;
import com.dousheng.dto.resp.auth.LogoutRespDTO;

public interface AuthService {
    AuthenticateRespDTO authenticate(AuthenticateReqDTO requestParam);
    RegisterRespDTO register(RegisterReqDTO requestParam);
    LoginRespDTO login(LoginReqDTO requestParam);
    LogoutRespDTO logout(LogoutReqDTO requestParam);
}
