package com.bing.rabbitmqtest.service;

import com.bing.rabbitmqtest.RabbitmqTestApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author sunyibing
 * @date 2024/3/25
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MessageRabbitMQImplTest {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void run1(){
        stringRedisTemplate.opsForValue().set("id", "22");
        stringRedisTemplate.opsForHash().put("hash","id", "22");
    }
}