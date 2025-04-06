package com.dousheng.api.common.web.interceptor;

import com.dousheng.api.common.convention.exception.ClientException;
import com.dousheng.api.common.web.middleware.Auth;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import static com.dousheng.api.common.convention.errorcode.BaseErrorCode.CLIENT_AUTH_ERROR;

/**
 * 认证拦截器
 */
@Component
@RequiredArgsConstructor
public class AuthInterceptor implements HandlerInterceptor {
    private final Auth auth;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!auth.isAllowed(request)) {
            throw new ClientException(CLIENT_AUTH_ERROR);
        }
        return true;
    }
}
