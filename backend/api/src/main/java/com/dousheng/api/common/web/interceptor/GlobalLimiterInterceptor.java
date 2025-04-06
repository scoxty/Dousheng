package com.dousheng.api.common.web.interceptor;

import com.dousheng.api.common.convention.exception.ClientException;
import com.dousheng.api.common.web.middleware.GlobalLimiter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import static com.dousheng.api.common.convention.errorcode.BaseErrorCode.SERVICE_RATE_LIMIT_ERROR;

/**
 * 全局限流拦截器
 */
@Component
@RequiredArgsConstructor
public class GlobalLimiterInterceptor implements HandlerInterceptor {
    private final GlobalLimiter globalLimiter;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!globalLimiter.isAllowed()) {
            throw new ClientException(SERVICE_RATE_LIMIT_ERROR);
        }
        return true;
    }
}
