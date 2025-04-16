package com.dousheng.api.common.web.middleware;

import com.dousheng.api.toolkit.IPUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

import static com.dousheng.api.common.constant.RedisKeyConstant.AUTH_COUNT_LIMIT_BY_IP_KEY;

/**
 * 基于 IP 的访问次数限制器
 */
@Component
@RequiredArgsConstructor
public class CountLimiterByIP {

    private final RedisTemplate redisTemplate;

    private static final long LIMITER_TIME = 1000 * 60 * 60; // 1 h
    private static final int MAX_COUNT = 12;

    public boolean isAllowed(HttpServletRequest request) {
        String ip = IPUtil.getRequestIp(request);
        String key = String.format(AUTH_COUNT_LIMIT_BY_IP_KEY, ip);
        redisTemplate.opsForValue().setIfAbsent(key, 0, LIMITER_TIME, TimeUnit.MILLISECONDS);
        Integer cnt = (Integer) redisTemplate.opsForValue().get(key);

        if (cnt < MAX_COUNT) {
            redisTemplate.opsForValue().increment(key);
            return true;
        }

        return false;
    }
}
