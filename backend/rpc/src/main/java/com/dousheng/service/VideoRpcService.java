package com.dousheng.service;

import com.dousheng.dto.req.video.*;
import com.dousheng.dto.resp.video.*;

public interface VideoRpcService {
    PublishRespDTO publish(PublishReqDTO requestParam);
    GetPublicListRespDTO getPublicList(GetPublicListReqDTO requestParam);
    GetVideoDetailRespDTO getVideoDetail(GetVideoDetailReqDTO requestParam);
    GetVideoDetailListRespDTO getVideoDetailList(GetVideoDetailListReqDTO requestParam);
    GetWorkCountRespDTO getWorkCount(GetWorkCountReqDTO requestParam);
}
