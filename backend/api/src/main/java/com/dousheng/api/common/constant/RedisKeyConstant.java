package com.dousheng.api.common.constant;

/**
 * Redis Key 常量类
 */
public class RedisKeyConstant {
    /**
     * Auth 基于 IP 的访问次数限制器前缀
     */
    public static final String AUTH_COUNT_LIMIT_BY_IP_KEY = "dousheng:auth-count-limit-by-ip:%s";

    /**
     * 基于 IP 的访问频率限制器前缀
     */
    public static final String RATE_LIMIT_BY_IP_KEY = "dousheng:rate-limit-by-ip:%s";

    /**
     *  全局访问频率限制器前缀
     */
    public static final String RATE_LIMIT_KEY = "dousheng:rate-limit";
}
