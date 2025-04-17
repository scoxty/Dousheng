package com.dousheng.service;

import com.dousheng.dto.req.user.*;
import com.dousheng.dto.resp.user.*;

public interface UserRpcService {
    GetUserInfoRespDTO getUserInfo(GetUserInfoReqDTO requestParam);
    AddUserRespDTO addUser(AddUserReqDTO requestParam);
    ModifyUserInfoRespDTO modifyUserInfo(ModifyUserInfoReqDTO requestParam);
    ModifyImageRespDTO modifyImage(ModifyImageReqDTO requestParam);
    IsExistRespDTO isExist(IsExistReqDTO requestParam);
    GetBaseUserInfoListRespDTO getBaseUserInfoList(GetBaseUserInfoListReqDTO requestParam);
}
