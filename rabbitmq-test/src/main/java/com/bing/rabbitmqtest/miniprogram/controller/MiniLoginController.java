package com.bing.rabbitmqtest.miniprogram.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author sunyibing
 * @date 2024/4/3
 */
@RestController
@RequestMapping(name = "", path = "/mini-login")
public class MiniLoginController {


    /**
     * 登录接口
     * @return
     */
    @PostMapping(name = "", path = "/login")
    public Map login (@RequestBody Map<String, String> params){
        Map result = new HashMap();
        result.put("data", "123");
        return result;
    }
}
