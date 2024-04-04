package com.bing.rabbitmqtest.controller;

import com.bing.rabbitmqtest.entity.User;
import com.bing.rabbitmqtest.service.BlockQueueServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 阻塞队列
 * @author sunyibing
 * @date 2024/4/2
 */
@RestController
@RequestMapping(name = "阻塞队列",path = "/queue")
public class BlockQueueController {

    @Resource
    private BlockQueueServiceImpl blockQueueService;

    @GetMapping(name="", path = "/run")
    public void run (){
        for(int i = 0 ; i < 100; i++){
            User user = new User();
            user.setId(String.valueOf(i));
            user.setName("name"+i);
            blockQueueService.put(user);
        }
    }
}
