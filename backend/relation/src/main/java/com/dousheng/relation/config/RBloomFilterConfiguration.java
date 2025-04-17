package com.dousheng.relation.config;

import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 布隆过滤器配置
 */
@Configuration(value = "rBloomFilterConfigurationByRelation")
public class RBloomFilterConfiguration {

    @Bean
    public RBloomFilter<String> relationCachePenetrationBloomFilter(RedissonClient redissonClient) {
        RBloomFilter<String> cachePenetrationBloomFilter = redissonClient.getBloomFilter("relationCachePenetrationBloomFilter");
        cachePenetrationBloomFilter.tryInit(100000000L, 0.001);
        return cachePenetrationBloomFilter;
    }

    @Bean
    public RBloomFilter<Long> followCachePenetrationBloomFilter(RedissonClient redissonClient) {
        RBloomFilter<Long> cachePenetrationBloomFilter = redissonClient.getBloomFilter("followCachePenetrationBloomFilter");
        cachePenetrationBloomFilter.tryInit(100000000L, 0.001);
        return cachePenetrationBloomFilter;
    }

    @Bean
    public RBloomFilter<Long> fanCachePenetrationBloomFilter(RedissonClient redissonClient) {
        RBloomFilter<Long> cachePenetrationBloomFilter = redissonClient.getBloomFilter("fanCachePenetrationBloomFilter");
        cachePenetrationBloomFilter.tryInit(100000000L, 0.001);
        return cachePenetrationBloomFilter;
    }
}