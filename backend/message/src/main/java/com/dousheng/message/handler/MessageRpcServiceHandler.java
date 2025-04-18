package com.dousheng.message.handler;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.dousheng.dto.req.message.GetMessageListReqDTO;
import com.dousheng.dto.resp.message.GetMessageListRespDTO;
import com.dousheng.message.common.convention.errorcode.BaseErrorCode;
import com.dousheng.message.common.convention.exception.AbstractException;
import com.dousheng.message.service.MessageService;
import com.dousheng.service.MessageRpcService;
import lombok.RequiredArgsConstructor;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Component;

@DubboService
@Component
@RequiredArgsConstructor
public class MessageRpcServiceHandler implements MessageRpcService {

    private static final Log log = LogFactory.get();

    private final MessageService messageService;

    @Override
    public GetMessageListRespDTO getMessageList(GetMessageListReqDTO requestParam) {
        GetMessageListRespDTO respDTO = new GetMessageListRespDTO();
        try {
            respDTO = messageService.getMessageList(requestParam);
            log.info("[getMessageList] success: req={}, resp={}", requestParam, respDTO);
        } catch (AbstractException e) {
            // 已知业务异常
            respDTO.setCode(e.getErrorCode());
            respDTO.setMessage(e.getMessage());
            log.error("[getMessageList] error: req={}, resp={}, err={}", requestParam, respDTO, e);
        } catch (Throwable e) {
            // 未知系统异常
            respDTO.setCode(BaseErrorCode.SERVICE_ERROR.code());
            respDTO.setMessage(BaseErrorCode.SERVICE_ERROR.message());
            log.error("[getMessageList] error: req={}, resp={}, err={}", requestParam, respDTO, e);
        }
        return respDTO;
    }
}
