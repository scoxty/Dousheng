package com.dousheng.user.service.impl;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.dousheng.dto.req.user.GetUserInfoReqDTO;
import com.dousheng.dto.req.user.ModifyImageReqDTO;
import com.dousheng.dto.req.user.ModifyUserInfoReqDTO;
import com.dousheng.dto.resp.user.GetUserInfoRespDTO;
import com.dousheng.dto.resp.user.ModifyImageRespDTO;
import com.dousheng.dto.resp.user.ModifyUserInfoRespDTO;
import com.dousheng.user.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private static final Log log = LogFactory.get();

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
