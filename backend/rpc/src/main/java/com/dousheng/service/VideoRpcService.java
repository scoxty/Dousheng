package com.dousheng.service;

import com.dousheng.dto.req.video.*;
import com.dousheng.dto.resp.video.*;

public interface VideoRpcService {
    PublishRespDTO publish(PublishReqDTO requestParam);
    ChangeVideoToPrivateRespDTO changeVideoToPrivate(ChangeVideoToPrivateReqDTO requestParam);
    ChangeVideoToPublicRespDTO changeVideoToPublic(ChangeVideoToPublicReqDTO requestParam);
    GetBaseVideoListRespDTO getBaseVideoList(GetBaseVideoListReqDTO requestParam);
    GetPublicListRespDTO getPublicList(GetPublicListReqDTO requestParam);
    GetPrivateListRespDTO getPrivateList(GetPrivateListReqDTO requestParam);
    GetVideoDetailRespDTO getVideoDetail(GetVideoDetailReqDTO requestParam);
    GetVideoDetailListRespDTO getVideoDetailList(GetVideoDetailListReqDTO requestParam);
    GetWorkCountRespDTO getWorkCount(GetWorkCountReqDTO requestParam);
}
