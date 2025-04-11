package com.dousheng.favorite.handler;

import com.dousheng.dto.req.favorite.GetTotalLikeMeCountReqDTO;
import com.dousheng.dto.req.favorite.GetVideoLikeCountsReqDTO;
import com.dousheng.dto.resp.favorite.GetTotalLikeMeCountRespDTO;
import com.dousheng.dto.resp.favorite.GetVideoLikeCountsRespDTO;
import com.dousheng.service.FavoriteRpcService;
import org.apache.dubbo.config.annotation.DubboService;

@DubboService
public class FavoriteRpcServiceHandler implements FavoriteRpcService {
    @Override
    public GetTotalLikeMeCountRespDTO getTotalLikeMeCount(GetTotalLikeMeCountReqDTO requestParam) {
        GetTotalLikeMeCountRespDTO mockResp = GetTotalLikeMeCountRespDTO.builder().code("0").message("success").totalLikeMeCount(0).build();
        return mockResp;
    }

    @Override
    public GetVideoLikeCountsRespDTO getVideoLikeCounts(GetVideoLikeCountsReqDTO requestParam) {
        GetVideoLikeCountsRespDTO mockResp = GetVideoLikeCountsRespDTO.builder().code("0").message("success").likeCounts(0).build();
        return mockResp;
    }
}
