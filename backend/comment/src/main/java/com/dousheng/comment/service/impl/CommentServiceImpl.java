package com.dousheng.comment.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dousheng.comment.common.convention.exception.ClientException;
import com.dousheng.comment.common.convention.exception.ServiceException;
import com.dousheng.comment.common.enums.CommentActionTypeEnum;
import com.dousheng.comment.dao.entity.CommentDO;
import com.dousheng.comment.dao.mapper.CommentMapper;
import com.dousheng.comment.mq.message.InteractionIndicatorMsg;
import com.dousheng.comment.mq.message.OperateDBMsg;
import com.dousheng.comment.service.CommentService;
import com.dousheng.dto.common.CommentDTO;
import com.dousheng.dto.common.UserInfoDTO;
import com.dousheng.dto.req.comment.CommentActionReqDTO;
import com.dousheng.dto.req.comment.GetCommentListReqDTO;
import com.dousheng.dto.req.comment.GetVideoCommentCountsReqDTO;
import com.dousheng.dto.req.user.GetBaseUserInfoListReqDTO;
import com.dousheng.dto.req.video.GetBaseVideoListReqDTO;
import com.dousheng.dto.req.video.IsExistReqDTO;
import com.dousheng.dto.resp.comment.CommentActionRespDTO;
import com.dousheng.dto.resp.comment.GetCommentListRespDTO;
import com.dousheng.dto.resp.comment.GetVideoCommentCountsRespDTO;
import com.dousheng.dto.resp.user.GetBaseUserInfoListRespDTO;
import com.dousheng.dto.resp.video.GetBaseVideoListRespDTO;
import com.dousheng.dto.resp.video.IsExistRespDTO;
import com.dousheng.service.UserRpcService;
import com.dousheng.service.VideoRpcService;
import io.netty.util.internal.ThreadLocalRandom;
import lombok.RequiredArgsConstructor;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.dousheng.comment.common.constant.MqConstant.COMMENT_OPERATE_DB_TOPIC;
import static com.dousheng.comment.common.constant.MqConstant.INTERACTION_INDICATOR_TOPIC;
import static com.dousheng.comment.common.constant.RedisCacheConstant.*;
import static com.dousheng.comment.common.constant.SuccessBaseRespConstant.SUCCESS_CODE;
import static com.dousheng.comment.common.constant.SuccessBaseRespConstant.SUCCESS_MESSAGE;
import static com.dousheng.comment.common.enums.CommentActionTypeEnum.CANCEL_COMMENT;
import static com.dousheng.comment.common.enums.CommentActionTypeEnum.COMMENT;
import static com.dousheng.comment.common.enums.CommentErrorCodeEnum.*;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl extends ServiceImpl<CommentMapper, CommentDO> implements CommentService {

    @DubboReference(timeout = 4000, retries = 3, loadbalance = "roundrobin", check = false)
    private UserRpcService userRpcService;
    @DubboReference(timeout = 4000, retries = 3, loadbalance = "roundrobin", check = false)
    private VideoRpcService videoRpcService;

    private final RedisTemplate redisTemplate;
    private final RedissonClient redissonClient;
    private final RocketMQTemplate rocketMQTemplate;
    private final RBloomFilter<Long> commentCachePenetrationBloomFilter;

    @Override
    public CommentActionRespDTO commentAction(CommentActionReqDTO requestParam) {
        this.checkParam(requestParam);

        IsExistRespDTO isExistRespDTO = videoRpcService.isExist(
                IsExistReqDTO.builder()
                        .videoId(requestParam.getVideoId())
                        .build());
        if (!isExistRespDTO.getIsExist()) {
            throw new ClientException(VIDEO_NOT_EXIST);
        }

        if (requestParam.getActionType().equals(COMMENT.type)) {

            OperateDBMsg operateDBMsg = OperateDBMsg.builder()
                    .id(IdWorker.getId())
                    .fatherCommentId(requestParam.getFatherCommentId())
                    .authorId(requestParam.getAuthorId())
                    .videoId(requestParam.getVideoId())
                    .userId(requestParam.getUserId())
                    .content(requestParam.getContent())
                    .actionType(COMMENT.type)
                    .build();

            // 通过mq重试保证写入成功
            rocketMQTemplate.syncSendOrderly(
                    COMMENT_OPERATE_DB_TOPIC,
                    operateDBMsg,
                    requestParam.getUserId() + ":" + requestParam.getVideoId());
            redisTemplate.delete(String.format(COMMENT_COUNT_KEY, requestParam.getVideoId()));
            commentCachePenetrationBloomFilter.add(requestParam.getVideoId());

            // 评论指标上报
            rocketMQTemplate.syncSend(
                    INTERACTION_INDICATOR_TOPIC,
                    InteractionIndicatorMsg.builder().
                            source("Comment-Service").
                            actionType(COMMENT.type).
                            userId(requestParam.getUserId()).
                            videoId(requestParam.getVideoId()).
                            build());

            return CommentActionRespDTO.builder()
                    .code(SUCCESS_CODE)
                    .message(SUCCESS_MESSAGE)
                    .commentDTO(BeanUtil.copyProperties(operateDBMsg, CommentDTO.class))
                    .build();
        } else {

            LambdaQueryWrapper<CommentDO> queryWrapper = Wrappers.lambdaQuery(CommentDO.class)
                    .eq(CommentDO::getId, requestParam.getCommentId())
                    .eq(CommentDO::getVideoId, requestParam.getVideoId())
                    .eq(CommentDO::getUserId, requestParam.getUserId());
            CommentDO commentDO = baseMapper.selectOne(queryWrapper);

            if (commentDO == null) {
                throw new ServiceException(COMMENT_NOT_EXIST);
            }

            OperateDBMsg operateDBMsg = OperateDBMsg.builder()
                    .id(requestParam.getCommentId())
                    .videoId(requestParam.getVideoId())
                    .userId(requestParam.getUserId())
                    .actionType(CANCEL_COMMENT.type)
                    .build();

            // 通过mq重试保证删除成功
            rocketMQTemplate.syncSendOrderly(
                    COMMENT_OPERATE_DB_TOPIC,
                    operateDBMsg,
                    requestParam.getUserId() + ":" + requestParam.getVideoId()
            );

            redisTemplate.delete(String.format(COMMENT_COUNT_KEY, requestParam.getVideoId()));

            // 评论指标上报
            rocketMQTemplate.syncSend(
                    INTERACTION_INDICATOR_TOPIC,
                    InteractionIndicatorMsg.builder().
                            source("Comment-Service").
                            actionType(CANCEL_COMMENT.type).
                            userId(requestParam.getUserId()).
                            videoId(requestParam.getVideoId()).
                            build());

            return CommentActionRespDTO.builder()
                    .code(SUCCESS_CODE)
                    .message(SUCCESS_MESSAGE)
                    .build();
        }
    }

    @Override
    public GetCommentListRespDTO getCommentList(GetCommentListReqDTO requestParam) {
        this.checkParam(requestParam);

        if (!commentCachePenetrationBloomFilter.contains(requestParam.getVideoId())) {
            return GetCommentListRespDTO.builder()
                    .code(SUCCESS_CODE)
                    .message(SUCCESS_MESSAGE)
                    .page(0)
                    .totalPage(0)
                    .totalCount(0)
                    .commentList(List.of())
                    .build();
        }

        Page<CommentDO> pageReq = new Page<>(requestParam.getPage(), requestParam.getPageSize());
        LambdaQueryWrapper<CommentDO> queryWrapper = Wrappers.lambdaQuery(CommentDO.class)
                .eq(CommentDO::getVideoId, requestParam.getVideoId())
                .orderByDesc(CommentDO::getCreateTime);
        List<CommentDO> commentDOList = baseMapper.selectPage(pageReq, queryWrapper).getRecords();

        HashMap<Long, Long> fatherCommentIdToUserId = new HashMap<>();
        List<Long> userIdsReq = new ArrayList<>();
        for (CommentDO commentDO : commentDOList) {
            userIdsReq.add(commentDO.getUserId());
            fatherCommentIdToUserId.put(commentDO.getId(), commentDO.getUserId());
        }

        GetBaseUserInfoListRespDTO getBaseUserInfoListRespDTO = userRpcService.getBaseUserInfoList(
                GetBaseUserInfoListReqDTO.builder()
                        .userIds(userIdsReq)
                        .build()
        );

        List<CommentDTO> commentDTOList = new ArrayList<>();
        for (CommentDO commentDO : commentDOList) {
            CommentDTO commentDTO = CommentDTO.builder()
                    .id(commentDO.getId())
                    .fatherCommentId(commentDO.getFatherCommentId())
                    .authorId(commentDO.getAuthorId())
                    .videoId(commentDO.getVideoId())
                    .userId(commentDO.getUserId())
                    .content(commentDO.getContent())
                    .isLiked(0)
                    .likeCount(0)
                    .createTime(commentDO.getCreateTime())
                    .build();

            for (UserInfoDTO userInfoDTO : getBaseUserInfoListRespDTO.getUserInfos()) {
                if (userInfoDTO.getId().equals(commentDO.getUserId())) {
                    commentDTO.setAvatar(userInfoDTO.getAvatar());
                    commentDTO.setUsername(userInfoDTO.getName());
                }
                if (userInfoDTO.getId().equals(fatherCommentIdToUserId.get(commentDO.getFatherCommentId()))) {
                    commentDTO.setReplayUsername(userInfoDTO.getName());
                }
            }

            commentDTOList.add(commentDTO);
        }

        return GetCommentListRespDTO.builder()
                .code(SUCCESS_CODE)
                .message(SUCCESS_MESSAGE)
                .page(requestParam.getPage())
                .totalPage((int) pageReq.getPages())
                .totalCount((int) pageReq.getTotal())
                .commentList(commentDTOList)
                .build();
    }

    @Override
    public GetVideoCommentCountsRespDTO getVideoCommentCounts(GetVideoCommentCountsReqDTO requestParam) {
        this.checkParam(requestParam);

        if (!commentCachePenetrationBloomFilter.contains(requestParam.getVideoId())) {
            return GetVideoCommentCountsRespDTO.builder()
                    .code(SUCCESS_CODE)
                    .message(SUCCESS_MESSAGE)
                    .commentCounts(0)
                    .build();
        }

        Integer count = (Integer) redisTemplate.opsForValue().get(String.format(COMMENT_COUNT_KEY, requestParam.getVideoId()));
        if (count != null) {
            return GetVideoCommentCountsRespDTO.builder()
                    .code(SUCCESS_CODE)
                    .message(SUCCESS_MESSAGE)
                    .commentCounts(count)
                    .build();
        }

        RLock lock = redissonClient.getLock(String.format(LOCK_COMMENT_COUNT_KEY, requestParam.getVideoId()));
        lock.lock();
        try {
            count = (Integer) redisTemplate.opsForValue().get(String.format(COMMENT_COUNT_KEY, requestParam.getVideoId()));
            if (count != null) {
                return GetVideoCommentCountsRespDTO.builder()
                        .code(SUCCESS_CODE)
                        .message(SUCCESS_MESSAGE)
                        .commentCounts(count)
                        .build();
            }

            LambdaQueryWrapper<CommentDO> queryWrapper = Wrappers.lambdaQuery(CommentDO.class)
                   .eq(CommentDO::getVideoId, requestParam.getVideoId());
            count = baseMapper.selectCount(queryWrapper).intValue();

            redisTemplate.opsForValue().set(
                    String.format(COMMENT_COUNT_KEY, requestParam.getVideoId()),
                    count, COMMENT_COUNT_BASE_TTL + ThreadLocalRandom.current().nextInt(31),
                    TimeUnit.SECONDS);
        } finally {
            lock.unlock();
        }

        return GetVideoCommentCountsRespDTO.builder()
                .code(SUCCESS_CODE)
                .message(SUCCESS_MESSAGE)
                .commentCounts(count)
                .build();
    }


    public void checkParam(CommentActionReqDTO requestParam) {
        if (requestParam == null) {
            throw new ClientException(REQUEST_PARAM_NULL);
        }
        if (requestParam.getVideoId() == null) {
            throw new ClientException(VIDEO_ID_NULL);
        }
        if (requestParam.getActionType() == null) {
            throw new ClientException(ACTION_TYPE_NULL);
        }
        if (!CommentActionTypeEnum.isValid(requestParam.getActionType())) {
            throw new ClientException(ACTION_TYPE_INVALID);
        }

        if (requestParam.getActionType().equals(COMMENT.type)) {
            if (StrUtil.isBlank(requestParam.getContent())) {
                throw new ClientException(CONTENT_NULL);
            }
            if (requestParam.getAuthorId() == null) {
                throw new ClientException(AUTHOR_ID_NULL);
            }
        } else {
            if (requestParam.getCommentId() == null) {
                throw new ClientException(COMMENT_ID_NULL);
            }
        }
    }

    public void checkParam(GetVideoCommentCountsReqDTO requestParam) {
        if (requestParam == null) {
            throw new ClientException(REQUEST_PARAM_NULL);
        }

        if (requestParam.getVideoId() == null) {
            throw new ClientException(VIDEO_ID_NULL);
        }
    }

    public void checkParam(GetCommentListReqDTO requestParam) {
        if (requestParam == null) {
            throw new ClientException(REQUEST_PARAM_NULL);
        }
        if (requestParam.getVideoId() == null) {
            throw new ClientException(VIDEO_ID_NULL);
        }
        if (requestParam.getPage() == null || requestParam.getPage() < 1) {
            throw new ClientException(PAGE_PARAM_ERROR);
        }
        if (requestParam.getPageSize() == null || requestParam.getPageSize() < 1) {
            throw new ClientException(PAGE_PARAM_ERROR);
        }
    }
}
