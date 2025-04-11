package com.dousheng.api.toolkit;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import com.dousheng.api.dto.req.ModifyUserInfoReqDTO;
import com.dousheng.api.dto.resp.UserInfoDTO;

/**
 * 本人只负责后端实现，为避免改前端牵一发动全身，因此在后端做适配
 */
public class PackerUtil {
    public static UserInfoDTO packApiUserInfo(com.dousheng.dto.common.UserInfoDTO requestParam) {
        return UserInfoDTO.builder()
                .id(StrUtil.toString(requestParam.getId()))
                .nickname(requestParam.getName())
                .imoocNum(requestParam.getDoushengNum())
                .face(requestParam.getAvatar())
                .sex(requestParam.getSex())
                .birthday(requestParam.getBirthday())
                .country(requestParam.getCountry())
                .province(requestParam.getProvince())
                .city(requestParam.getCity())
                .district(requestParam.getDistrict())
                .description(requestParam.getSignature())
                .bgImg(requestParam.getBackgroundImage())
                .canImoocNumBeUpdated(requestParam.getCanDoushengNumBeUpdated())
                .createdTime(requestParam.getCreateTime())
                .updatedTime(requestParam.getUpdateTime())
                .userToken(requestParam.getUserToken())
                .myFollowsCounts(requestParam.getMyFollowsCounts())
                .myFansCounts(requestParam.getMyFansCounts())
                .totalLikeMeCounts(requestParam.getTotalLikeMeCounts())
                .build();
    }

    public static com.dousheng.dto.common.UserInfoDTO packRpcUserInfo(UserInfoDTO requestParam) {
        Long id = null;
        if (StrUtil.isNotBlank(requestParam.getId())) {
            id = NumberUtil.parseLong(requestParam.getId());
        }

        return com.dousheng.dto.common.UserInfoDTO.builder()
                .id(id)
                .name(requestParam.getNickname())
                .doushengNum(requestParam.getImoocNum())
                .sex(requestParam.getSex())
                .birthday(requestParam.getBirthday())
                .country(requestParam.getCountry())
                .province(requestParam.getProvince())
                .city(requestParam.getCity())
                .district(requestParam.getDistrict())
                .signature(requestParam.getDescription())
                .avatar(requestParam.getFace())
                .backgroundImage(requestParam.getBgImg())
                .canDoushengNumBeUpdated(requestParam.getCanImoocNumBeUpdated())
                .createTime(requestParam.getCreatedTime())
                .updateTime(requestParam.getUpdatedTime())
                .userToken(requestParam.getUserToken())
                .build();
    }

    public static com.dousheng.dto.common.UserInfoDTO packRpcUserInfo(ModifyUserInfoReqDTO requestParam) {
        Long id = null;
        if (StrUtil.isNotBlank(requestParam.getId())) {
            id = NumberUtil.parseLong(requestParam.getId());
        }

        return com.dousheng.dto.common.UserInfoDTO.builder()
                .id(id)
                .name(requestParam.getNickname())
                .doushengNum(requestParam.getImoocNum())
                .sex(requestParam.getSex())
                .birthday(requestParam.getBirthday())
                .country(requestParam.getCountry())
                .province(requestParam.getProvince())
                .city(requestParam.getCity())
                .district(requestParam.getDistrict())
                .signature(requestParam.getDescription())
                .avatar(requestParam.getFace())
                .backgroundImage(requestParam.getBgImg())
                .canDoushengNumBeUpdated(requestParam.getCanImoocNumBeUpdated())
                .build();
    }
}
