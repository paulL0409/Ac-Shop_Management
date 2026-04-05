package com.acShop.config;

import io.github.bucket4j.distributed.proxy.ProxyManager;
import io.github.bucket4j.redis.lettuce.cas.LettuceBasedProxyManager;
import io.lettuce.core.RedisClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RateLimitConfig {
    @Bean
    public RedisClient redisClient(){
        return  RedisClient.create("redis://localhost:6379");
    }

    @Bean
    public ProxyManager<byte[]> proxyManager(RedisClient redisClient){
        return LettuceBasedProxyManager.builderFor(redisClient)
                .build();

    }
}
