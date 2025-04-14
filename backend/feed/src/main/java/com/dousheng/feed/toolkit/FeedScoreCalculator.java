package com.dousheng.feed.toolkit;

import com.dousheng.dto.common.VideoInfoDTO;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import static com.dousheng.feed.common.enums.CommentTypeEnum.CANCEL_COMMENT;
import static com.dousheng.feed.common.enums.CommentTypeEnum.COMMENT;
import static com.dousheng.feed.common.enums.LikeTypeEnum.DISLIKE;
import static com.dousheng.feed.common.enums.LikeTypeEnum.LIKE;

public class FeedScoreCalculator {

    // 权重参数（可调）
    private static final int LIKE_WEIGHT = 2;
    private static final int COMMENT_WEIGHT = 3;

    // 衰减速度控制参数（单位小时，越大越慢）
    private static final double DECAY_LAMBDA = 24.0;

    /**
     * 计算视频热度分数（用于 feed:hot ZSet）
     * @return 计算后的热度分值（double）
     */
    public static double calculate(VideoInfoDTO videoInfoDTO) {
        double baseScore = 10 + videoInfoDTO.getLikeCounts() * LIKE_WEIGHT + videoInfoDTO.getCommentCounts() * COMMENT_WEIGHT; // 视频受欢迎程度

        LocalDateTime publishDateTime = LocalDateTime.ofInstant(videoInfoDTO.getCreateTime().toInstant(), ZoneId.systemDefault());
        long hoursPassed = Duration.between(publishDateTime, LocalDateTime.now()).toHours();
        double timeDecay = Math.exp(-1.0 * hoursPassed / DECAY_LAMBDA);  // 时效控制: e^(-t/λ)

        return baseScore * timeDecay;
    }
}