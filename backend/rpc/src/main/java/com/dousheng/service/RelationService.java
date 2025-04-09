package com.dousheng.service;

import com.dousheng.dto.req.relation.GetFansCountReqDTO;
import com.dousheng.dto.req.relation.GetFollowsCountReqDTO;
import com.dousheng.dto.resp.relation.GetFansCountRespDTO;
import com.dousheng.dto.resp.relation.GetFollowsCountRespDTO;

public interface RelationService {
    GetFollowsCountRespDTO getFollowsCount(GetFollowsCountReqDTO requestParam);
    GetFansCountRespDTO getFansCount(GetFansCountReqDTO requestParam);
}
