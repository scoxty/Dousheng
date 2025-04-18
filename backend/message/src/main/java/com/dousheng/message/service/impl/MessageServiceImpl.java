package com.dousheng.message.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dousheng.dto.common.MessageDTO;
import com.dousheng.dto.req.message.GetMessageListReqDTO;
import com.dousheng.dto.req.relation.IsFriendReqDTO;
import com.dousheng.dto.resp.message.GetMessageListRespDTO;
import com.dousheng.dto.resp.relation.IsFriendRespDTO;
import com.dousheng.message.common.convention.exception.ClientException;
import com.dousheng.message.common.convention.exception.RemoteException;
import com.dousheng.message.common.convention.exception.ServiceException;
import com.dousheng.message.dao.entity.MessageDO;
import com.dousheng.message.dao.mapper.MessageMapper;
import com.dousheng.message.service.MessageService;
import com.dousheng.service.RelationRpcService;
import com.dousheng.service.UserRpcService;
import lombok.RequiredArgsConstructor;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.stereotype.Service;

import java.rmi.ServerException;
import java.util.List;

import static com.dousheng.message.common.constant.SuccessBaseRespConstant.SUCCESS_CODE;
import static com.dousheng.message.common.constant.SuccessBaseRespConstant.SUCCESS_MESSAGE;
import static com.dousheng.message.common.enums.MessageErrorCodeEnum.*;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl extends ServiceImpl<MessageMapper, MessageDO> implements MessageService {

    @DubboReference(timeout = 4000, retries = 3, loadbalance = "roundrobin", check = false)
    private RelationRpcService relationRpcService;

    private final RocketMQTemplate rocketMQTemplate;

    @Override
    public GetMessageListRespDTO getMessageList(GetMessageListReqDTO requestParam) {
        this.checkParam(requestParam);

        IsFriendRespDTO isFriendRespDTO = relationRpcService.isFriend(
                IsFriendReqDTO.builder()
                        .userIdA(requestParam.getUserId())
                        .userIdB(requestParam.getFriendId())
                        .build()
        );
        if (!isFriendRespDTO.getCode().equals(SUCCESS_CODE)) {
            throw new RemoteException(isFriendRespDTO.getMessage());
        }

        if (!isFriendRespDTO.getIsFriend()) {
            throw new ServiceException(NOT_FRIEND);
        }

        Page<MessageDO> pageReq = new Page<>(requestParam.getPage(), requestParam.getPageSize());
        LambdaQueryWrapper<MessageDO> queryWrapper = Wrappers.lambdaQuery(MessageDO.class)
                .and(w -> w
                        .eq(MessageDO::getSenderId, requestParam.getUserId()).eq(MessageDO::getReceiverId, requestParam.getFriendId())
                        .or()
                        .eq(MessageDO::getSenderId, requestParam.getFriendId()).eq(MessageDO::getReceiverId, requestParam.getUserId())
                )
                .orderByDesc(MessageDO::getCreateTime);
        List<MessageDO> messageDOList = baseMapper.selectPage(pageReq, queryWrapper).getRecords();
        List<MessageDTO> messageDTOList = BeanUtil.copyToList(messageDOList, MessageDTO.class);

        return GetMessageListRespDTO.builder()
                .code(SUCCESS_CODE)
                .message(SUCCESS_MESSAGE)
                .page(requestParam.getPage())
                .totalPage((int) pageReq.getPages())
                .totalCount((int) pageReq.getTotal())
                .messageList(messageDTOList)
                .build();
    }

    public void checkParam(GetMessageListReqDTO requestParam) {
        if (requestParam == null) {
            throw new ClientException(REQUEST_PARAM_NULL);
        }
        if (requestParam.getUserId() == null) {
            throw new ClientException(USER_ID_NULL);
        }
    }
}
