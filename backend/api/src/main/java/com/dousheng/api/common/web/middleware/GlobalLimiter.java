package com.dousheng.api.common.web.middleware;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

import static com.dousheng.api.common.constant.Lua.TOKEN_BUKEY_LUA_SCRIPT_PATH;
import static com.dousheng.api.common.constant.RedisKeyConstant.RATE_LIMIT_KEY;

/**
 * 全局限流拦截器
 */
@Component
@RequiredArgsConstructor
public class GlobalLimiter {
    private final RedisTemplate redisTemplate;

    private static final Long RESET_BUCKET_INTERNAL = 1000 * 60L; // 重置令牌桶的时间间隔为1min
    private static final Long INTERNAL_PER_PERMIT = 1000L; // 每个令牌产生的时间为1s
    private static final Long BUCKET_MAX_TOKEN = 1000L; // 最大令牌数量
    private static final Long INIT_TOKEN = 1000L; // 初始桶内得令牌数// key名

    public boolean isAllowed() {
        DefaultRedisScript<Long> script = new DefaultRedisScript<>();
        script.setScriptSource(new ResourceScriptSource(new ClassPathResource(TOKEN_BUKEY_LUA_SCRIPT_PATH)));
        script.setResultType(Long.class);

        ArrayList<String> keys = new ArrayList<>();
        keys.add(RATE_LIMIT_KEY);

        Long cnt = (Long) redisTemplate.execute(script,
                keys,
                INTERNAL_PER_PERMIT,
                System.currentTimeMillis(),
                INIT_TOKEN,
                BUCKET_MAX_TOKEN,
                RESET_BUCKET_INTERNAL);

        return cnt > 0;
    }
}
