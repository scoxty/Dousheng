package com.dousheng.video.service;

import com.dousheng.dto.req.video.GetPublishListReqDTO;
import com.dousheng.dto.req.video.GetWorkCountReqDTO;
import com.dousheng.dto.req.video.PublishReqDTO;
import com.dousheng.dto.resp.video.GetPublishListRespDTO;
import com.dousheng.dto.resp.video.GetWorkCountRespDTO;
import com.dousheng.dto.resp.video.PublishRespDTO;

public interface VideoService {
    PublishRespDTO publish(PublishReqDTO requestParam);
    GetPublishListRespDTO getPublishList(GetPublishListReqDTO requestParam);
    GetWorkCountRespDTO getWorkCount(GetWorkCountReqDTO requestParam);
}
