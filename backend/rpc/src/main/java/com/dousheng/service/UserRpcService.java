package com.dousheng.service;

import com.dousheng.dto.req.user.GetUserInfoReqDTO;
import com.dousheng.dto.req.user.ModifyImageReqDTO;
import com.dousheng.dto.req.user.ModifyUserInfoReqDTO;
import com.dousheng.dto.resp.user.GetUserInfoRespDTO;
import com.dousheng.dto.resp.user.ModifyImageRespDTO;
import com.dousheng.dto.resp.user.ModifyUserInfoRespDTO;

public interface UserRpcService {
    GetUserInfoRespDTO getUserInfo(GetUserInfoReqDTO requestParam);
    ModifyUserInfoRespDTO modifyUserInfo(ModifyUserInfoReqDTO requestParam);
    ModifyImageRespDTO modifyImage(ModifyImageReqDTO requestParam);
}
