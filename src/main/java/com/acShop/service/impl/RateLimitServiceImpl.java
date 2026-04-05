package com.acShop.service.impl;

import com.acShop.service.RateLimitService;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.BucketConfiguration;
import io.github.bucket4j.Refill;
import io.github.bucket4j.distributed.proxy.ProxyManager;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class RateLimitServiceImpl implements RateLimitService {

    private final ProxyManager<byte[]> proxyManager;

    public RateLimitServiceImpl(ProxyManager<byte[]> proxyManager) {
        this.proxyManager = proxyManager;
    }

    @Override
    public boolean tryConsumeLoginToken(String clientKey) {
        Bucket bucket = proxyManager.builder().build(clientKey.getBytes(StandardCharsets.UTF_8), this::buildConfig);
        return bucket.tryConsume(1);
    }

    private BucketConfiguration buildConfig() {
        return BucketConfiguration.builder()
                .addLimit(Bandwidth.classic(5, Refill.greedy(5, Duration.ofMinutes(1))))
                .build();
    }




}
