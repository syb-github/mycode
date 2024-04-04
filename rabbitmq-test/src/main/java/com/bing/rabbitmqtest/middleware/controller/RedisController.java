package com.bing.rabbitmqtest.middleware.controller;

import com.bing.rabbitmqtest.middleware.service.RedisTest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * redis测试服务
 * @author sunyibing
 * @date 2024/3/25
 */
@RestController
@RequestMapping(name = "redis测试服务1", path = "/redis")
public class RedisController {

    @Resource
    private RedisTest redisTest;
    @GetMapping(name = "redis执行", path = "/run")
    public void run () throws InterruptedException {
        redisTest.run();
    }

}
