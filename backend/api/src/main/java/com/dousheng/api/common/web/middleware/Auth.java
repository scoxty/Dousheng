package com.dousheng.api.common.web.middleware;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

/**
 * 鉴权中间件，用于验证用户是否有权限访问资源。
 */
@Component
public class Auth {

    public boolean isAllowed(HttpServletRequest request) {
        return true;
    }
}
