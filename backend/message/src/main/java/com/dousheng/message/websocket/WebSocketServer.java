package com.dousheng.message.websocket;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.alibaba.fastjson2.JSON;
import com.dousheng.dto.common.MessageDTO;
import com.dousheng.dto.req.auth.AuthenticateReqDTO;
import com.dousheng.dto.resp.auth.AuthenticateRespDTO;
import com.dousheng.message.common.biz.SpringContextUtils;
import com.dousheng.message.mq.message.OperateDBMsg;
import com.dousheng.service.AuthRpcService;
import jakarta.websocket.*;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;

import static com.dousheng.message.common.constant.MqConstant.MESSAGE_OPERATE_DB_TOPIC;
import static com.dousheng.message.common.constant.SuccessBaseRespConstant.SUCCESS_CODE;

@Component
@ServerEndpoint("/chat/{userId}/{token}")
public class WebSocketServer {

    private static final Log log = LogFactory.get();

    private Session session = null;
    private Long userId;
    public final static ConcurrentHashMap<Long, WebSocketServer> users = new ConcurrentHashMap<>();

    @DubboReference(timeout = 4000, retries = 3, loadbalance = "roundrobin")
    private AuthRpcService authRpcService;

    private static RocketMQTemplate rocketMQTemplate;
    @Autowired
    public void setRocketMQTemplate(RocketMQTemplate rocketMQTemplate) {
        WebSocketServer.rocketMQTemplate = rocketMQTemplate;
    }

    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String userId,  @PathParam("token") String token) throws IOException {
        this.session = session;
        log.info("[WebSocketServer][onOpen] connected! userId={}", userId);

        Long userID = NumberUtil.parseLong(userId);
        this.userId = userID;

        // 解决注入失败的问题：手动从 Spring 获取
        if (this.authRpcService == null) {
            this.authRpcService = SpringContextUtils.getBean(AuthRpcService.class);
        }

        // 认证
        AuthenticateRespDTO respDTO = authRpcService.authenticate(
                AuthenticateReqDTO.builder()
                        .userId(userID)
                        .token(token)
                        .build()
        );
        if (!respDTO.getCode().equals(SUCCESS_CODE)) {
            log.error("[WebSocketServer][onOpen] authenticate error: {}", respDTO);
            session.close();
            return;
        }

        // 维护连接
        users.put(userID, this);
    }

    @OnClose
    public void onClose() {
        log.info("[WebSocketServer][onClose] disconnected! userId={}", this.userId);
        users.remove(this.userId);
    }

    @OnMessage
    public void onMessage(String message) {
        log.info("[WebSocketServer][onMessage] received message: {}", message);
        OperateDBMsg operateMsg = JSON.parseObject(message, OperateDBMsg.class);

        // 通过mq异步写入db，并通过重试保证写入成功
        rocketMQTemplate.syncSend(MESSAGE_OPERATE_DB_TOPIC, operateMsg);

        // 推送给接收方
        WebSocketServer receiver = users.get(operateMsg.getReceiverId());
        if (receiver != null) {
            log.info("[WebSocketServer] push message to user, toUserId={}, message={}", operateMsg.getReceiverId(), message);
            receiver.sendMessage(message);
        }

    }

    @OnError
    public void onError(Session session, Throwable error) {
        log.error("[WebSocketServer][onError] error: {}", error);
    }

    public void sendMessage(String message) {
        synchronized (this.session) {
            try {
                this.session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                log.error("[WebSocketServer][sendMessage] error: {}", e);
            }
        }
    }
}