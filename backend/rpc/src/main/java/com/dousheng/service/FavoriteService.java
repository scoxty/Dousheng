package com.dousheng.service;

import com.dousheng.dto.req.favorite.GetTotalLikeMeCountReqDTO;
import com.dousheng.dto.resp.favorite.GetTotalLikeMeCountRespDTO;

public interface FavoriteService {
    GetTotalLikeMeCountRespDTO getTotalLikeMeCount(GetTotalLikeMeCountReqDTO requestParam);
}
