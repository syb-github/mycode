package com.bing.rabbitmqtest.middleware.controller;

import com.bing.rabbitmqtest.service.LockServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Lock锁1
 * @author sunyibing
 * @date 2024/4/2
 */
@RestController
@RequestMapping(name="", value = "/lock")
public class LockController {


    @Resource
    private LockServiceImpl lockService;

    @GetMapping(name = "", path = "/run")
    private void run () throws InterruptedException {
        lockService.run();
    }
}
