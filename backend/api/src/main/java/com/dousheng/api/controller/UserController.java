package com.dousheng.api.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.dousheng.api.common.biz.user.UserContext;
import com.dousheng.api.common.convention.exception.ServiceException;
import com.dousheng.api.dto.resp.UserInfoDTO;
import com.dousheng.api.common.convention.result.Result;
import com.dousheng.api.common.convention.result.Results;
import com.dousheng.api.dto.req.ModifyUserInfoReqDTO;
import com.dousheng.dto.req.user.GetUserInfoReqDTO;
import com.dousheng.dto.req.user.ModifyImageReqDTO;
import com.dousheng.dto.resp.user.GetUserInfoRespDTO;
import com.dousheng.dto.resp.user.ModifyImageRespDTO;
import com.dousheng.dto.resp.user.ModifyUserInfoRespDTO;
import com.dousheng.service.UserRpcService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;

import static com.dousheng.api.common.constant.SuccessBaseRespConstant.SUCCESS_CODE;
import static com.dousheng.api.common.convention.errorcode.BaseErrorCode.SERVICE_ERROR;

@RestController
public class UserController {
    private static final Log log = LogFactory.get();

    @DubboReference(timeout = 5000, retries = 3, loadbalance = "roundrobin")
    private UserRpcService userRpcService;

    @GetMapping("/userInfo/query")
    public Result<UserInfoDTO> getUserInfo(@RequestParam Long userId) {
        GetUserInfoReqDTO reqDTO = GetUserInfoReqDTO.builder().userId(userId).build();
        GetUserInfoRespDTO respDTO = userRpcService.getUserInfo(reqDTO);
        if (respDTO.getCode().equals(SUCCESS_CODE)) {
            log.info("[getUserInfo] success: req={}, resp={}", userId, Results.success());
            UserInfoDTO userInfoDTO = BeanUtil.copyProperties(respDTO.getUserInfo(), UserInfoDTO.class);
            return Results.success(userInfoDTO);
        }
        Result<UserInfoDTO> result = new Result<>();
        result.setCode(respDTO.getCode());
        result.setMessage(respDTO.getMessage());
        log.error("[getUserInfo] error: req={}, resp={}", userId, respDTO);
        return result;
    }

    @PostMapping("/userInfo/modifyUserInfo")
    public Result<UserInfoDTO> modifyUserInfo(@RequestParam Integer type, @RequestBody ModifyUserInfoReqDTO requestParam) {
        com.dousheng.dto.req.user.ModifyUserInfoReqDTO reqDTO = new com.dousheng.dto.req.user.ModifyUserInfoReqDTO();
        reqDTO.setFromUserId(UserContext.getUserId());
        reqDTO.setType(type);
        reqDTO.setUserInfo(BeanUtil.copyProperties(requestParam, com.dousheng.dto.common.UserInfoDTO.class));
        ModifyUserInfoRespDTO respDTO = userRpcService.modifyUserInfo(reqDTO);
        if (respDTO.getCode().equals(SUCCESS_CODE)) {
            log.info("[modifyUserInfo] success: req={}, resp={}", reqDTO, respDTO);
            UserInfoDTO userInfoDTO = BeanUtil.copyProperties(respDTO.getUserInfo(), UserInfoDTO.class);
            return Results.success(userInfoDTO);
        }
        Result<UserInfoDTO> result = new Result<>();
        result.setCode(respDTO.getCode());
        result.setMessage(respDTO.getMessage());
        log.error("[modifyUserInfo] error: req={}, resp={}", reqDTO, respDTO);
        return result;
    }

    @PostMapping("/userInfo/modifyImage")
    public Result<UserInfoDTO> modifyImage(@RequestParam Long userId, @RequestParam Integer type, MultipartFile file) {
        String image;
        try {
            image = Base64.getEncoder().encodeToString(file.getBytes());
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), SERVICE_ERROR);
        }

        ModifyImageReqDTO reqDTO = ModifyImageReqDTO.builder().
                fromUserId(UserContext.getUserId()).
                toUserId(userId).
                type(type).
                image(image).build();

        ModifyImageRespDTO respDTO = userRpcService.modifyImage(reqDTO);
        if (respDTO.getCode().equals(SUCCESS_CODE)) {
            log.info("[modifyImage] success: req={}, resp={}", reqDTO, respDTO);
            UserInfoDTO userInfoDTO = BeanUtil.copyProperties(respDTO.getUserInfo(), UserInfoDTO.class);
            return Results.success(userInfoDTO);
        }
        Result<UserInfoDTO> result = new Result<>();
        result.setCode(respDTO.getCode());
        result.setMessage(respDTO.getMessage());
        log.error("[modifyImage] error: req={}, resp={}", reqDTO, respDTO);
        return result;
    }

}
