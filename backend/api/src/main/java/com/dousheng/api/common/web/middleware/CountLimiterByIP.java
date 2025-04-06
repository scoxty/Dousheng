package com.dousheng.api.common.web.middleware;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

import static com.dousheng.api.common.constant.RedisKeyConstant.AUTH_COUNT_LIMIT_BY_IP_KEY;

/**
 * 基于 IP 的访问次数限制器
 * 2小时内访问 100 次，超出则限流
 */
@Component
@RequiredArgsConstructor
public class CountLimiterByIP {

    private final RedisTemplate redisTemplate;

    private static final long LIMITER_TIME = 1000; // 1 秒
    private static final int MAX_COUNT = 1;

    public boolean isAllowed(HttpServletRequest request) {
        String addr = request.getRemoteAddr();
        String ip = addr.split(":")[0];
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
