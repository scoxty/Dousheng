package com.dousheng.api.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.dousheng.api.dto.req.RegisterReqDTO;
import com.dousheng.api.dto.resp.UserInfoDTO;
import com.dousheng.api.common.convention.result.Result;
import com.dousheng.api.common.convention.result.Results;
import com.dousheng.api.dto.req.LoginReqDTO;
import com.dousheng.dto.req.auth.LogoutReqDTO;
import com.dousheng.dto.resp.auth.LoginRespDTO;
import com.dousheng.dto.resp.auth.LogoutRespDTO;
import com.dousheng.dto.resp.auth.RegisterRespDTO;
import com.dousheng.service.AuthRpcService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.dousheng.api.common.constant.SuccessBaseRespConstant.SUCCESS_CODE;

@RestController
public class AuthController {
    private static final Log log = LogFactory.get();

    @DubboReference(timeout = 5000, retries = 3, loadbalance = "roundrobin")
    private AuthRpcService authRpcService;

    @PostMapping("/passport/register")
    public Result<Void> register(@RequestBody RegisterReqDTO requestParam) {
        com.dousheng.dto.req.auth.RegisterReqDTO reqDTO = BeanUtil.copyProperties(requestParam, com.dousheng.dto.req.auth.RegisterReqDTO.class);
        RegisterRespDTO respDTO = authRpcService.register(reqDTO);
        if (respDTO.getCode().equals(SUCCESS_CODE)) {
            log.info("[register] success: req={}, resp={}", requestParam, Results.success());
            return Results.success();
        }
        log.error("[register] error: req={}, resp={}", requestParam, respDTO);
        return Results.failure(respDTO.getCode(), respDTO.getMessage());
    }

    @PostMapping("/passport/login")
    public Result<UserInfoDTO> login(@RequestBody LoginReqDTO requestParam) {
        com.dousheng.dto.req.auth.LoginReqDTO reqDTO = BeanUtil.copyProperties(requestParam, com.dousheng.dto.req.auth.LoginReqDTO.class);
        LoginRespDTO respDTO = authRpcService.login(reqDTO);
        if (respDTO.getCode().equals(SUCCESS_CODE)) {
            UserInfoDTO userInfoDTO = BeanUtil.copyProperties(respDTO.getUserInfo(), UserInfoDTO.class);
            log.info("[login] success: req={}, resp={}", requestParam, Results.success(userInfoDTO));
            return Results.success(userInfoDTO);
        }
        Result<UserInfoDTO> result = new Result<>();
        result.setCode(respDTO.getCode());
        result.setMessage(respDTO.getMessage());
        log.error("[login] error: req={}, resp={}", requestParam, result);
        return result;
    }

    @PostMapping("/passport/logout")
    public Result<Void> logout(@RequestParam Long userId) {
        LogoutReqDTO reqDTO = LogoutReqDTO.builder().userId(userId).build();
        LogoutRespDTO respDTO = authRpcService.logout(reqDTO);
        if (respDTO.getCode().equals(SUCCESS_CODE)) {
            log.info("[logout] success: req={}, resp={}", reqDTO, Results.success());
            return Results.success();
        }
        log.error("[logout] error: req={}, resp={}", reqDTO, Results.failure(respDTO.getCode(), respDTO.getMessage()));
        return Results.failure(respDTO.getCode(), respDTO.getMessage());
    }
}
