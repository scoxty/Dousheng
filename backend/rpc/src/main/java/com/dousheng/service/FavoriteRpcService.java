package com.dousheng.service;

import com.dousheng.dto.req.favorite.GetTotalLikeMeCountReqDTO;
import com.dousheng.dto.req.favorite.GetVideoLikeCountsReqDTO;
import com.dousheng.dto.req.favorite.IsLikeVideoReqDTO;
import com.dousheng.dto.resp.favorite.GetTotalLikeMeCountRespDTO;
import com.dousheng.dto.resp.favorite.GetVideoLikeCountsRespDTO;
import com.dousheng.dto.resp.favorite.IsLikeVideoRespDTO;

public interface FavoriteRpcService {
    GetTotalLikeMeCountRespDTO getTotalLikeMeCount(GetTotalLikeMeCountReqDTO requestParam);
    GetVideoLikeCountsRespDTO getVideoLikeCounts(GetVideoLikeCountsReqDTO requestParam);
    IsLikeVideoRespDTO isLikeVideo(IsLikeVideoReqDTO requestParam);
}
