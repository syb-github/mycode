package com.bing.rabbitmqtest.middleware.controller;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author sunyibing
 * @date 2024/4/12
 */
@Controller
@RequestMapping(path = "/rocketmq")
public class RocketMQController {

    @Resource
    private RocketMQTemplate rocketMQTemplate;
    @RequestMapping("/sendMessage")
    @ResponseBody
    public String sendMessage(String topic, String message){
        // 这里message 可以是对象
        try {
//            rocketMQTemplate.convertAndSend(topic ,message);
//            rocketMQTemplate.createAndStartTransactionMQProducer()
            // 事务消息  搭配 RocketMQLocalTransactionListener 监听接口
            rocketMQTemplate.sendMessageInTransaction("provider-group"
                    ,"test-topic"
                    , MessageBuilder.withPayload(message).build()
                    , message);
            return "发送成功";
        } catch (Exception e) {
            e.printStackTrace();
            return "发送失败";
        }
    }
}
