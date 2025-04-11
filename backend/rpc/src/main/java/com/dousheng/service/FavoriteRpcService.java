package com.dousheng.service;

import com.dousheng.dto.req.favorite.GetTotalLikeMeCountReqDTO;
import com.dousheng.dto.req.favorite.GetVideoLikeCountsReqDTO;
import com.dousheng.dto.resp.favorite.GetTotalLikeMeCountRespDTO;
import com.dousheng.dto.resp.favorite.GetVideoLikeCountsRespDTO;

public interface FavoriteRpcService {
    GetTotalLikeMeCountRespDTO getTotalLikeMeCount(GetTotalLikeMeCountReqDTO requestParam);
    GetVideoLikeCountsRespDTO getVideoLikeCounts(GetVideoLikeCountsReqDTO requestParam);
}
