package com.dousheng.api.toolkit;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import com.dousheng.api.dto.req.ModifyUserInfoReqDTO;
import com.dousheng.api.dto.req.PublishReqDTO;
import com.dousheng.api.dto.resp.IndexVideoDTO;
import com.dousheng.api.dto.resp.UserInfoDTO;
import com.dousheng.api.dto.resp.VideoInfoDTO;

import java.util.ArrayList;
import java.util.List;

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

    public static com.dousheng.dto.common.VideoInfoDTO packRpcVideoInfo(PublishReqDTO requestParam) {
        Long authorId = null;
        if (StrUtil.isNotBlank(requestParam.getId())) {
            authorId = NumberUtil.parseLong(requestParam.getVlogerId());
        }

        return com.dousheng.dto.common.VideoInfoDTO.builder().
                authorId(authorId).
                playUrl(requestParam.getUrl()).
                coverUrl(requestParam.getCover()).
                title(requestParam.getTitle()).
                width(requestParam.getWidth()).
                height(requestParam.getHeight()).
                build();
    }

    public static List<VideoInfoDTO> packApiPublishList(List<com.dousheng.dto.common.VideoInfoDTO> requestParam) {
        List<VideoInfoDTO> videoInfoDTOList = new ArrayList<>();
        for (int i = 0; i < requestParam.size(); i++) {
            com.dousheng.dto.common.VideoInfoDTO videoInfoDTO = requestParam.get(i);
            videoInfoDTOList.add(VideoInfoDTO.builder().
                    id(StrUtil.toString(videoInfoDTO.getId())).
                    vlogerId(StrUtil.toString(videoInfoDTO.getAuthorId())).
                    url(videoInfoDTO.getPlayUrl()).
                    cover(videoInfoDTO.getCoverUrl()).
                    title(videoInfoDTO.getTitle()).
                    width(videoInfoDTO.getWidth()).
                    height(videoInfoDTO.getHeight()).
                    likeCounts(videoInfoDTO.getLikeCounts()).
                    commentsCounts(videoInfoDTO.getCommentCounts()).
                    isPrivate(videoInfoDTO.getIsPrivate()).
                    createdTime(videoInfoDTO.getCreateTime()).
                    updatedTime(videoInfoDTO.getUpdateTime()).
                    build());
        }
        return videoInfoDTOList;
    }

    public static List<IndexVideoDTO> packApiIndexFeed(List<com.dousheng.dto.common.VideoInfoDTO> requestParam) {
        List<IndexVideoDTO> indexVideoDTOList = new ArrayList<>();
        for (int i = 0; i < requestParam.size(); i++) {
            com.dousheng.dto.common.VideoInfoDTO videoInfoDTO = requestParam.get(i);
            indexVideoDTOList.add(IndexVideoDTO.builder().
                    vlogId(StrUtil.toString(videoInfoDTO.getId())).
                    vlogerId(StrUtil.toString(videoInfoDTO.getAuthorId())).
                    vlogerName(videoInfoDTO.getAuthorName()).
                    vlogerFace(videoInfoDTO.getAuthorAvatar()).
                    url(videoInfoDTO.getPlayUrl()).
                    cover(videoInfoDTO.getCoverUrl()).
                    content(videoInfoDTO.getTitle()).
                    width(videoInfoDTO.getWidth()).
                    height(videoInfoDTO.getHeight()).
                    likeCounts(videoInfoDTO.getLikeCounts()).
                    commentsCounts(videoInfoDTO.getCommentCounts()).
                    isPrivate(videoInfoDTO.getIsPrivate()).
                    doIFollowVloger(videoInfoDTO.getIsFollow()).
                    doILikeThisVlog(videoInfoDTO.getIsFavorite()).
                    build());
        }
        return indexVideoDTOList;
    }
}
