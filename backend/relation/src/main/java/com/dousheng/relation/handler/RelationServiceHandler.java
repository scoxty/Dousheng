package com.dousheng.relation.handler;

import com.dousheng.dto.req.relation.GetFansCountReqDTO;
import com.dousheng.dto.req.relation.GetFollowsCountReqDTO;
import com.dousheng.dto.resp.relation.GetFansCountRespDTO;
import com.dousheng.dto.resp.relation.GetFollowsCountRespDTO;
import com.dousheng.service.RelationService;
import org.apache.dubbo.config.annotation.DubboService;

@DubboService
public class RelationServiceHandler implements RelationService {
    @Override
    public GetFollowsCountRespDTO getFollowsCount(GetFollowsCountReqDTO requestParam) {
        GetFollowsCountRespDTO mockResp = GetFollowsCountRespDTO.builder().code("0").message("success").followsCount(0).build();
        return mockResp;
    }

    @Override
    public GetFansCountRespDTO getFansCount(GetFansCountReqDTO requestParam) {
        GetFansCountRespDTO mockResp = GetFansCountRespDTO.builder().code("0").message("success").fansCount(0).build();
        return mockResp;
    }
}
