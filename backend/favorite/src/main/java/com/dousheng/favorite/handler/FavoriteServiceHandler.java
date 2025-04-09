package com.dousheng.favorite.handler;

import com.dousheng.dto.req.favorite.GetTotalLikeMeCountReqDTO;
import com.dousheng.dto.resp.favorite.GetTotalLikeMeCountRespDTO;
import com.dousheng.service.FavoriteService;
import org.apache.dubbo.config.annotation.DubboService;

@DubboService
public class FavoriteServiceHandler implements FavoriteService {
    @Override
    public GetTotalLikeMeCountRespDTO getTotalLikeMeCount(GetTotalLikeMeCountReqDTO requestParam) {
        GetTotalLikeMeCountRespDTO mockResp = GetTotalLikeMeCountRespDTO.builder().code("0").message("success").totalLikeMeCount(0).build();
        return mockResp;
    }
}
