package com.dousheng.service;

import com.dousheng.dto.req.feed.GetIndexFeedReqDTO;
import com.dousheng.dto.resp.feed.GetIndexFeedRespDTO;

public interface FeedRpcService {
    GetIndexFeedRespDTO getIndexFeed(GetIndexFeedReqDTO requestParam);
}
