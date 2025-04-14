package com.dousheng.video.config;

import com.dousheng.video.dao.entity.VideoDO;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.Expiry;
import org.checkerframework.checker.index.qual.NonNegative;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

@Configuration
public class LocalCacheConfig {

    @Bean
    public Cache<Long, List<VideoDO>> publicListLocalCache() {
        return Caffeine.newBuilder()
                .maximumSize(1024)
                .expireAfter(new Expiry<Long, List<VideoDO>>() {
                    private final long base = TimeUnit.MINUTES.toNanos(30);
                    private final long bound = TimeUnit.SECONDS.toNanos(30);

                    @Override
                    public long expireAfterCreate(Long userId, List<VideoDO> publicList, long l) {
                        long jitter = ThreadLocalRandom.current().nextLong(bound + 1);
                        return base + jitter;
                    }

                    @Override
                    public long expireAfterUpdate(Long userId, List<VideoDO> publicList, long l, @NonNegative long l1) {
                        long jitter = ThreadLocalRandom.current().nextLong(bound + 1);
                        return base + jitter;
                    }

                    @Override
                    public long expireAfterRead(Long userId, List<VideoDO> publicList, long l, @NonNegative long l1) {
                        long jitter = ThreadLocalRandom.current().nextLong(bound + 1);
                        return base + jitter;
                    }
                })
                .build();
    }

    @Bean
    public Cache<Long, VideoDO> videoDetailLocalCache() {
        return Caffeine.newBuilder()
                .maximumSize(1024)
                .expireAfter(new Expiry<Long, VideoDO>() {

                    private final long base = TimeUnit.MINUTES.toNanos(30);
                    private final long bound = TimeUnit.SECONDS.toNanos(30);

                    @Override
                    public long expireAfterCreate(Long videoId, VideoDO videoDO, long l) {
                        long jitter = ThreadLocalRandom.current().nextLong(bound + 1);
                        return base + jitter;
                    }

                    @Override
                    public long expireAfterUpdate(Long videoId, VideoDO videoDO, long l, @NonNegative long l1) {
                        long jitter = ThreadLocalRandom.current().nextLong(bound + 1);
                        return base + jitter;
                    }

                    @Override
                    public long expireAfterRead(Long videoId, VideoDO videoDO, long l, @NonNegative long l1) {
                        long jitter = ThreadLocalRandom.current().nextLong(bound + 1);
                        return base + jitter;
                    }
                })
                .build();
    }

    @Bean
    public Cache<Long, Integer> workCountLocalCache() {
        return Caffeine.newBuilder()
               .maximumSize(1024)
                .expireAfter(new Expiry<Long, Integer>() {
                    private final long base = TimeUnit.MINUTES.toNanos(30);
                    private final long bound = TimeUnit.SECONDS.toNanos(30);

                    @Override
                    public long expireAfterCreate(Long userId, Integer workCount, long l) {
                        long jitter = ThreadLocalRandom.current().nextLong(bound + 1);
                        return base + jitter;
                    }

                    @Override
                    public long expireAfterUpdate(Long userId, Integer workCount, long l, @NonNegative long l1) {
                        long jitter = ThreadLocalRandom.current().nextLong(bound + 1);
                        return base + jitter;
                    }

                    @Override
                    public long expireAfterRead(Long userId, Integer workCount, long l, @NonNegative long l1) {
                        long jitter = ThreadLocalRandom.current().nextLong(bound + 1);
                        return base + jitter;
                    }
                })
                .build();
    }
}
