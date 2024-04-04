package com.bing.rabbitmqtest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.*;

/**
 * @author sunyibing
 * @date 2024/3/25
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitmqTestApplicationTest {
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void run1(){
        stringRedisTemplate.opsForValue().set("id", "111");
        stringRedisTemplate.opsForHash().put("hash","id", "22");

        LocalDateTime now = LocalDateTime.now();
        long threadId = Thread.currentThread().getId();
        // key  业务：实体类：业务id
        String prefix = "login:thread";
        long nowSecond = System.currentTimeMillis();
        // 生成序号
        String nowStr = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        // redis 自增长
        long icr = stringRedisTemplate.opsForValue().increment(prefix + ":icr:"+nowStr);
        long id = nowSecond << 32 | icr;
        System.out.println(id);
    }
}