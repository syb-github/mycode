package com.bing.rabbitmqtest.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * redisson分布式锁
 * @author sunyibing
 * @date 2024/4/2
 */
@Configuration
public class RedissonConfig {
    @Bean
    public RedissonClient getRedissonClient() {
        Config redissonConfig = new Config();
        redissonConfig.useSingleServer().setAddress("redis://127.0.0.1:6379").setPassword("syb951007");
        return Redisson.create(redissonConfig);
    }
}
