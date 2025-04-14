package com.dousheng.video.service;

import com.dousheng.dto.req.video.*;
import com.dousheng.dto.resp.video.*;

public interface VideoService {
    PublishRespDTO publish(PublishReqDTO requestParam);
    ChangeVideoToPrivateRespDTO changeVideoToPrivate(ChangeVideoToPrivateReqDTO requestParam);
    ChangeVideoToPublicRespDTO changeVideoToPublic(ChangeVideoToPublicReqDTO requestParam);
    GetPublicListRespDTO getPublicList(GetPublicListReqDTO requestParam);
    GetPrivateListRespDTO getPrivateList(GetPrivateListReqDTO requestParam);
    GetVideoDetailRespDTO getVideoDetail(GetVideoDetailReqDTO requestParam);
    GetVideoDetailListRespDTO getVideoDetailList(GetVideoDetailListReqDTO requestParam);
    GetWorkCountRespDTO getWorkCount(GetWorkCountReqDTO requestParam);
}
