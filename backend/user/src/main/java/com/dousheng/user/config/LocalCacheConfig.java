package com.dousheng.user.config;

import com.dousheng.dto.common.UserInfoDTO;
import com.dousheng.user.dao.entity.UserDO;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Configuration
public class LocalCacheConfig {

    @Bean
    public Cache<Long, UserDO> userInfoLocalCache() {
        return CacheBuilder.newBuilder().
                expireAfterAccess(Duration.ofMinutes(30)).
                maximumSize(1024).build();
    }

    @Bean
    public Cache<String, Long> userIdLocalCache() {
        return CacheBuilder.newBuilder().
                expireAfterAccess(Duration.ofMinutes(30)).
                maximumSize(1024).build();
    }
}
