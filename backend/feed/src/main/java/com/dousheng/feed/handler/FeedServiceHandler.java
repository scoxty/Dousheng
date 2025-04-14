package com.dousheng.feed.handler;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.dousheng.dto.req.feed.GetIndexFeedReqDTO;
import com.dousheng.dto.resp.feed.GetIndexFeedRespDTO;
import com.dousheng.feed.common.convention.errorcode.BaseErrorCode;
import com.dousheng.feed.common.convention.exception.AbstractException;
import com.dousheng.feed.service.FeedService;
import com.dousheng.service.FeedRpcService;
import lombok.RequiredArgsConstructor;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Component;

@DubboService
@Component
@RequiredArgsConstructor
public class FeedServiceHandler implements FeedRpcService {
    private static final Log log = LogFactory.get();

    private final FeedService feedService;

    @Override
    public GetIndexFeedRespDTO getIndexFeed(GetIndexFeedReqDTO requestParam) {
        GetIndexFeedRespDTO respDTO = new GetIndexFeedRespDTO();
        try {
            respDTO = feedService.getIndexFeed(requestParam);
            log.info("[getIndexFeed] success: req={}, resp={}", requestParam, respDTO);
        } catch (AbstractException e) {
            // 已知业务异常处理
            respDTO.setCode(e.getErrorCode());
            respDTO.setMessage(e.getErrorMessage());
            log.error("[getIndexFeed] error: req={}, resp={}, err={}", requestParam, respDTO, e);
        } catch (Throwable e) {
            // 未知系统异常处理
            respDTO.setCode(BaseErrorCode.SERVICE_ERROR.code());
            respDTO.setMessage(BaseErrorCode.SERVICE_ERROR.message());
            log.error("[getIndexFeed] error: req={}, resp={}, err={}", requestParam, respDTO, e);
        }
        return respDTO;
    }
}
