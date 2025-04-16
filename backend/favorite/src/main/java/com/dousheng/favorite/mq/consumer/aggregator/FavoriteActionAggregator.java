package com.dousheng.favorite.mq.consumer.aggregator;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.dousheng.favorite.mq.message.FavoriteActionMsg;
import com.dousheng.favorite.service.FavoriteService;
import com.dousheng.favorite.service.impl.FavoriteServiceImpl;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicReference;

@Component
@RequiredArgsConstructor
public class FavoriteActionAggregator {

    private static final Log log = LogFactory.get();
    private final AtomicReference<ConcurrentHashMap<String, FavoriteActionMsg>> bufferRef =
            new AtomicReference<>(new ConcurrentHashMap<>());

    private final FavoriteServiceImpl favoriteService;

    private final ExecutorService flushExecutor = new ThreadPoolExecutor(
            1, 1,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(1024),
            r -> {
                Thread t = new Thread(r);
                t.setName("favorite-aggregator-flusher");
                t.setDaemon(true);
                return t;
            },
            new ThreadPoolExecutor.CallerRunsPolicy()
    );

    @PostConstruct
    public void init() {
        Executors.newSingleThreadScheduledExecutor(r -> {
            Thread t = new Thread(r);
            t.setName("favorite-aggregator-scheduler");
            t.setDaemon(true);
            return t;
        }).scheduleAtFixedRate(this::flush, 1000, 1000, TimeUnit.MILLISECONDS);
    }

    public void add(FavoriteActionMsg msg) {
        String key = msg.getUserId() + ":" + msg.getVideoId();
        bufferRef.get().put(key, msg);  // 覆盖写
    }

    private void flush() {
        ConcurrentHashMap<String, FavoriteActionMsg> oldMap = bufferRef.getAndSet(new ConcurrentHashMap<>());

        if (oldMap.isEmpty()) return;

        // 异步批处理（避免阻塞调度线程）
        flushExecutor.submit(() -> {
            try {
                favoriteService.processBatch(new ArrayList<>(oldMap.values()));
            } catch (Exception e) {
                log.error("[FavoriteAggregator] batch process error: {}", e);
            }
        });
    }
}
