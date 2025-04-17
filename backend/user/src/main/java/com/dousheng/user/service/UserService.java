package com.dousheng.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dousheng.dto.req.user.*;
import com.dousheng.dto.resp.user.*;
import com.dousheng.user.dao.entity.UserDO;

public interface UserService extends IService<UserDO> {
    GetUserInfoRespDTO getUserInfo(GetUserInfoReqDTO requestParam);
    AddUserRespDTO addUser(AddUserReqDTO requestParam);
    ModifyUserInfoRespDTO modifyUserInfo(ModifyUserInfoReqDTO requestParam);
    ModifyImageRespDTO modifyImage(ModifyImageReqDTO requestParam);
    IsExistRespDTO isExist(IsExistReqDTO requestParam);
    GetBaseUserInfoListRespDTO getBaseUserInfoList(GetBaseUserInfoListReqDTO requestParam);
}
