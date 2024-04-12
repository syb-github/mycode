package com.bing.rabbitmqtest.middleware.service;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

/**
 * @author sunyibing
 * @date 2024/4/12
 */
@Service
@RocketMQMessageListener(topic = "test-topic", consumerGroup = "my-consumer_test-topic")
public class RocketMqServiceImpl implements RocketMQListener<String> {
    @Override
    public void onMessage(String message) {
        System.out.println("消费RocketMq:"+message);
    }
}
