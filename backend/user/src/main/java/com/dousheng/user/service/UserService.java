package com.dousheng.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dousheng.dto.req.user.AddUserReqDTO;
import com.dousheng.dto.req.user.GetUserInfoReqDTO;
import com.dousheng.dto.req.user.ModifyImageReqDTO;
import com.dousheng.dto.req.user.ModifyUserInfoReqDTO;
import com.dousheng.dto.resp.user.AddUserRespDTO;
import com.dousheng.dto.resp.user.GetUserInfoRespDTO;
import com.dousheng.dto.resp.user.ModifyImageRespDTO;
import com.dousheng.dto.resp.user.ModifyUserInfoRespDTO;
import com.dousheng.user.dao.entity.UserDO;

public interface UserService extends IService<UserDO> {
    GetUserInfoRespDTO getUserInfo(GetUserInfoReqDTO requestParam);
    AddUserRespDTO addUser(AddUserReqDTO requestParam);
    ModifyUserInfoRespDTO modifyUserInfo(ModifyUserInfoReqDTO requestParam);
    ModifyImageRespDTO modifyImage(ModifyImageReqDTO requestParam);
}
