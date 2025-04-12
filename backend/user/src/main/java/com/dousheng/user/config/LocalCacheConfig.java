package com.dousheng.user.config;

import com.dousheng.user.dao.entity.UserDO;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.Expiry;
import org.checkerframework.checker.index.qual.NonNegative;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

@Configuration
public class LocalCacheConfig {

    @Bean
    public Cache<Long, UserDO> userInfoLocalCache() {
        return Caffeine.newBuilder()
                .maximumSize(1024)
                .expireAfter(new Expiry<Long, UserDO>() {
                    private final long base = TimeUnit.MINUTES.toNanos(30);
                    private final long bound = TimeUnit.SECONDS.toNanos(30);

                    @Override
                    public long expireAfterCreate(Long userId, UserDO userDO, long currentTime) {
                        long jitter = ThreadLocalRandom.current().nextLong(bound + 1);
                        return base + jitter;
                    }

                    @Override
                    public long expireAfterUpdate(Long userId, UserDO userDO, long currentTime, long currentDuration) {
                        long jitter = ThreadLocalRandom.current().nextLong(bound + 1);
                        return base + jitter;
                    }

                    @Override
                    public long expireAfterRead(Long userId, UserDO userDO, long currentTime, long currentDuration) {
                        long jitter = ThreadLocalRandom.current().nextLong(bound + 1);
                        return base + jitter;
                    }
                })
                .build();
    }

    @Bean
    public Cache<String, Long> userIdLocalCache() {
        return Caffeine.newBuilder()
                .maximumSize(1024)
                .expireAfter(new Expiry<String, Long>() {
                    private final long base = TimeUnit.MINUTES.toNanos(30);
                    private final long bound = TimeUnit.SECONDS.toNanos(30);


                    @Override
                    public long expireAfterCreate(String username, Long userId, long currentTime) {
                        long jitter = ThreadLocalRandom.current().nextLong(bound + 1);
                        return base + jitter;
                    }

                    @Override
                    public long expireAfterUpdate(String username, Long userId, long currentTime, long currentDuration) {
                        long jitter = ThreadLocalRandom.current().nextLong(bound + 1);
                        return base + jitter;
                    }

                    @Override
                    public long expireAfterRead(String username, Long userId, long currentTime, long currentDuration) {
                        long jitter = ThreadLocalRandom.current().nextLong(bound + 1);
                        return base + jitter;
                    }
                })
                .build();
    }
}
