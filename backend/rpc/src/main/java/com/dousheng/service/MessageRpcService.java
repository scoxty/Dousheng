package com.dousheng.service;

import com.dousheng.dto.req.message.GetMessageListReqDTO;
import com.dousheng.dto.resp.message.GetMessageListRespDTO;

public interface MessageRpcService {
    GetMessageListRespDTO getMessageList(GetMessageListReqDTO requestParam);
}
