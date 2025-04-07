package com.dousheng.api.controller;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.dousheng.api.dto.resp.UserInfoDTO;
import com.dousheng.api.common.convention.result.Result;
import com.dousheng.api.common.convention.result.Results;
import com.dousheng.api.dto.req.ModifyUserInfoReqDTO;
import com.dousheng.service.UserRpcService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UserController {
    private static final Log log = LogFactory.get();

    @DubboReference(timeout = 5000, retries = 3, loadbalance = "roundrobin")
    private UserRpcService userRpcService;

    @GetMapping("/userInfo/query")
    public Result<UserInfoDTO> getUserInfo(@RequestParam String userId) {
        return Results.success(new UserInfoDTO());
    }

    @PostMapping("/userInfo/modifyUserInfo")
    public Result<UserInfoDTO> modifyUserInfo(@RequestParam String type, @RequestBody ModifyUserInfoReqDTO requestParam) {
        return Results.success(new UserInfoDTO());
    }

    @PostMapping("/userInfo/modifyImage")
    public Result<UserInfoDTO> modifyImage(@RequestParam String userId, @RequestParam Integer type, MultipartFile file) {
        return Results.success(new UserInfoDTO());
    }

}
