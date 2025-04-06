package com.dousheng.api.common.web.interceptor;

import com.dousheng.api.common.convention.exception.ClientException;
import com.dousheng.api.common.web.middleware.RateLimiterByIP;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import static com.dousheng.api.common.convention.errorcode.BaseErrorCode.CLIENT_RATE_LIMIT_ERROR;

/**
 * 基于IP的访问频率限流拦截器
 */
@Component
@RequiredArgsConstructor
public class RateLimiterByIPInterceptor implements HandlerInterceptor {
    private final RateLimiterByIP rateLimiter;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!rateLimiter.isAllowed(request)) {
            throw new ClientException(CLIENT_RATE_LIMIT_ERROR);
        }
        return true;
    }
}
