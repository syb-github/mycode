package com.bing.rabbitmqtest.middleware.controller;

import com.bing.rabbitmqtest.common.Result;
import com.bing.rabbitmqtest.middleware.entity.User;
import com.bing.rabbitmqtest.middleware.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author sunyibing
 * @date 2024/4/4
 */
@RestController
@RequestMapping(name="用户信息", path = "/user")
public class UserController {

    @Resource
    private UserService userService;
    @PostMapping(name = "查询用户信息", path = "/find")
    public Result run () {
        return Result.success(userService.findByAge(3));
    }
}
