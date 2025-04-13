package com.dousheng.video.config;

import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 布隆过滤器配置
 */
@Configuration(value = "rBloomFilterConfigurationByVideo")
public class RBloomFilterConfiguration {

    @Bean
    public RBloomFilter<Long> userIdCachePenetrationBloomFilter(RedissonClient redissonClient) {
        RBloomFilter<Long> cachePenetrationBloomFilter = redissonClient.getBloomFilter("userIdCachePenetrationBloomFilter");
        cachePenetrationBloomFilter.tryInit(100000000L, 0.001);
        return cachePenetrationBloomFilter;
    }
}