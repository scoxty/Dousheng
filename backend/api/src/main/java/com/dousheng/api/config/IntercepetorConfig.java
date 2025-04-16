package com.dousheng.api.config;

import com.dousheng.api.common.web.interceptor.AuthInterceptor;
import com.dousheng.api.common.web.interceptor.CountLimiterByIPInterceptor;
import com.dousheng.api.common.web.interceptor.GlobalLimiterInterceptor;
import com.dousheng.api.common.web.interceptor.RateLimiterByIPInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class IntercepetorConfig implements WebMvcConfigurer {

    private final AuthInterceptor authInterceptor;
    private final GlobalLimiterInterceptor globalLimiterInterceptor;
    private final CountLimiterByIPInterceptor countLimiterInterceptor;
    private final RateLimiterByIPInterceptor rateLimiterInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 全局限流
        registry.addInterceptor(globalLimiterInterceptor).addPathPatterns("/**");

        // 限制单个IP的访问次数
        registry.addInterceptor(countLimiterInterceptor).addPathPatterns(
                "/passport/register",
                "/passport/login",
                "/passport/logout",
                "/vlog/publish"
        );

       // 限制单个IP的访问频率
        registry.addInterceptor(rateLimiterInterceptor).addPathPatterns(
                "/userInfo/query",
                "/userInfo/modifyUserInfo",
                "/userInfo/modifyImage",
                "/vlog/detail",
                "/vlog/changeToPrivate",
                "/vlog/changeToPublic",
                "/vlog/myPublicList",
                "/vlog/myPrivateList",
                "/vlog/indexList",
                "/vlog/followList",
                "/vlog/friendList",
                "/vlog/like",
                "/vlog/unlike",
                "/vlog/myLikedList",
                "/vlog/totalLikedCounts",
                "/comment/like",
                "/comment/unlike",
                "/comment/create",
                "/comment/delete",
                "/comment/list",
                "/comment/counts",
                "/fans/follow",
                "/fans/cancel",
                "/fans/queryDoIFollowVloger",
                "/fans/queryMyFollows",
                "/fans/queryMyFans",
                "/message/chat",
                "/message/action"
        );

        // 认证中间件，确保只有登录用户才能访问
        registry.addInterceptor(authInterceptor).addPathPatterns(
                "/userInfo/modifyUserInfo",
                "/userInfo/modifyImage",
                "/vlog/publish",
                "/vlog/changeToPrivate",
                "/vlog/changeToPublic",
                "/vlog/myPrivateList",
                "/vlog/followList",
                "/vlog/friendList",
                "/vlog/like",
                "/vlog/unlike",
                "/comment/like",
                "/comment/unlike",
                "/comment/create",
                "/comment/delete",
                "/fans/follow",
                "/fans/cancel",
                "/fans/queryDoIFollowVloger",
                "/fans/queryMyFollows",
                "/fans/queryMyFans",
                "/message/chat",
                "/message/action"
        );

    }
}
