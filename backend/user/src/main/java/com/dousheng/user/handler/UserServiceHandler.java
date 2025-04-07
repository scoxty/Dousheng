package com.dousheng.user.handler;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.dousheng.dto.req.user.GetUserInfoReqDTO;
import com.dousheng.dto.req.user.ModifyImageReqDTO;
import com.dousheng.dto.req.user.ModifyUserInfoReqDTO;
import com.dousheng.dto.resp.user.GetUserInfoRespDTO;
import com.dousheng.dto.resp.user.ModifyImageRespDTO;
import com.dousheng.dto.resp.user.ModifyUserInfoRespDTO;
import com.dousheng.service.UserRpcService;
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
        return null;
    }

    @Override
    public ModifyUserInfoRespDTO modifyUserInfo(ModifyUserInfoReqDTO requestParam) {
        return null;
    }

    @Override
    public ModifyImageRespDTO modifyImage(ModifyImageReqDTO requestParam) {
        return null;
    }
}
