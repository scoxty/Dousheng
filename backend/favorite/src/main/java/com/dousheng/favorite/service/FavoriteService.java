package com.dousheng.favorite.service;

import com.dousheng.dto.req.favorite.*;
import com.dousheng.dto.resp.favorite.*;

public interface FavoriteService {
    FavoriteActionRespDTO favoriteAction(FavoriteActionReqDTO requestParam);
    GetFavoriteListRespDTO getFavoriteList(GetFavoriteListReqDTO requestParam);
    GetTotalLikeMeCountRespDTO getTotalLikeMeCount(GetTotalLikeMeCountReqDTO requestParam);
    GetVideoLikeCountsRespDTO getVideoLikeCounts(GetVideoLikeCountsReqDTO requestParam);
    IsLikeVideoRespDTO isLikeVideo(IsLikeVideoReqDTO requestParam);
}
