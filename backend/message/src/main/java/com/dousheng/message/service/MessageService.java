package com.dousheng.message.service;

import com.dousheng.dto.req.message.GetMessageListReqDTO;
import com.dousheng.dto.resp.message.GetMessageListRespDTO;

public interface MessageService {
    GetMessageListRespDTO getMessageList(GetMessageListReqDTO requestParam);
}
