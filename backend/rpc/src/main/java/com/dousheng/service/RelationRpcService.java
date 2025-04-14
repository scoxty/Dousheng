package com.dousheng.service;

import com.dousheng.dto.req.relation.GetFansCountReqDTO;
import com.dousheng.dto.req.relation.GetFollowsCountReqDTO;
import com.dousheng.dto.req.relation.IsFollowReqDTO;
import com.dousheng.dto.resp.relation.GetFansCountRespDTO;
import com.dousheng.dto.resp.relation.GetFollowsCountRespDTO;
import com.dousheng.dto.resp.relation.IsFollowRespDTO;

public interface RelationRpcService {
    GetFollowsCountRespDTO getFollowsCount(GetFollowsCountReqDTO requestParam);
    GetFansCountRespDTO getFansCount(GetFansCountReqDTO requestParam);
    IsFollowRespDTO isFollow(IsFollowReqDTO requestParam);
}
