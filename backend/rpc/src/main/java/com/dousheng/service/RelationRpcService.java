package com.dousheng.service;

import com.dousheng.dto.req.relation.*;
import com.dousheng.dto.resp.relation.*;

public interface RelationRpcService {
    FollowActionRespDTO followAction(FollowActionReqDTO requestParam);
    IsFollowRespDTO isFollow(IsFollowReqDTO requestParam);
    GetFollowListRespDTO getFollowList(GetFollowListReqDTO requestParam);
    GetFollowsCountRespDTO getFollowsCount(GetFollowsCountReqDTO requestParam);
    GetFanListRespDTO getFanList(GetFanListReqDTO requestParam);
    GetFansCountRespDTO getFansCount(GetFansCountReqDTO requestParam);
    IsFriendRespDTO isFriend(IsFriendReqDTO requestParam);
}
