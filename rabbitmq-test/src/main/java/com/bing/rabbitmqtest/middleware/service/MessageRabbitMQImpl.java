package com.bing.rabbitmqtest.middleware.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MessageRabbitMQImpl {

    @Resource
    private RabbitTemplate rabbitTemplate;

    public void sendMessage(String id){
//        rabbitTemplate.convertAndSend("syb_queue21", id);
        rabbitTemplate.convertAndSend("","","");
    }
}
