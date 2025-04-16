package com.dousheng.service;

import com.dousheng.dto.req.favorite.*;
import com.dousheng.dto.resp.favorite.*;

public interface FavoriteRpcService {
    FavoriteActionRespDTO favoriteAction(FavoriteActionReqDTO requestParam);
    GetFavoriteListRespDTO getFavoriteList(GetFavoriteListReqDTO requestParam);
    GetTotalLikeMeCountRespDTO getTotalLikeMeCount(GetTotalLikeMeCountReqDTO requestParam);
    GetVideoLikeCountsRespDTO getVideoLikeCounts(GetVideoLikeCountsReqDTO requestParam);
    IsLikeVideoRespDTO isLikeVideo(IsLikeVideoReqDTO requestParam);
}
