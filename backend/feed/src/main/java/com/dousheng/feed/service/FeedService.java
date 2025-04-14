package com.dousheng.feed.service;

import com.dousheng.dto.req.feed.GetIndexFeedReqDTO;
import com.dousheng.dto.resp.feed.GetIndexFeedRespDTO;

public interface FeedService {
    GetIndexFeedRespDTO getIndexFeed(GetIndexFeedReqDTO requestParam);
}
