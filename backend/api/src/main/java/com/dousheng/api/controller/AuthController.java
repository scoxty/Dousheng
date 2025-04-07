package com.dousheng.api.controller;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.dousheng.api.dto.resp.UserInfoDTO;
import com.dousheng.api.common.convention.result.Result;
import com.dousheng.api.common.convention.result.Results;
import com.dousheng.api.dto.req.LoginReqDTO;
import com.dousheng.dto.req.auth.GetSMSCodeReqDTO;
import com.dousheng.dto.resp.auth.GetSMSCodeRespDTO;
import com.dousheng.service.AuthRpcService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    private static final Log log = LogFactory.get();

    @DubboReference(timeout = 5000, retries = 3, loadbalance = "roundrobin")
    private AuthRpcService authRpcService;

    @PostMapping("/passport/getSMSCode")
    public Result<Void> getSMSCode(@RequestParam String mobile) {
        return Results.success();
    }

    @PostMapping("/passport/login")
    public Result<UserInfoDTO> login(@RequestBody LoginReqDTO loginReqDTO) {
        return Results.success(new UserInfoDTO());
    }

    @PostMapping("/passport/logout")
    public Result<Void> logout(@RequestParam String userId) {
        return Results.success();
    }
}
