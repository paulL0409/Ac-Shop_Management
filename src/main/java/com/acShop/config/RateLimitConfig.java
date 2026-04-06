package com.acShop.config;

import io.github.bucket4j.distributed.proxy.ProxyManager;
import io.github.bucket4j.redis.lettuce.cas.LettuceBasedProxyManager;
import io.lettuce.core.RedisClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RateLimitConfig {

    @Value("${spring.data.redis.host}")
    private String redisHost;

    @Value("${spring.data.redis.port}")
    private int redisPort;

    @Bean
    public RedisClient redisClient(){
        return  RedisClient.create("redis://" + redisHost + ":" + redisPort);
    }

    @Bean
    public ProxyManager<byte[]> proxyManager(RedisClient redisClient){
        return LettuceBasedProxyManager.builderFor(redisClient)
                .build();

    }
}
