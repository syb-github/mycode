package com.bing.rabbitmqtest.middleware.controller;

import org.springframework.kafka.core.KafkaOperations;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.util.concurrent.FailureCallback;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.util.concurrent.SuccessCallback;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.kafka.support.SendResult;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author
 */ /*
 * kafkaDemo

 */
@RestController
@RequestMapping(name = "", path = "/kafka")
public class KafkaSampleController {
    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;

    @GetMapping(path = "/batchSend")
    public String batchSend(String topic, String key, String data) {
//        List<String> list = new ArrayList<>();
//        list.add("11");
//        list.add("22");
        kafkaTemplate.send(topic, key, data);
        return "success";
    }
    @GetMapping(path = "/send")
    public String send(String topic, String key, String data) {
//        kafkaTemplate.send(topic, key, data);

        // 发送消息回调1
        kafkaTemplate.send(topic, data).addCallback(new SuccessCallback<SendResult<String, String>>() {
            //成功的回调
            @Override
            public void onSuccess(SendResult<String, String> success) {
                // 消息发送到的topic
                String topic = success.getRecordMetadata().topic();
                // 消息发送到的分区
                int partition = success.getRecordMetadata().partition();
                // 消息在分区内的offset
                long offset = success.getRecordMetadata().offset();
                System.out.println("发送消息成功1:" + topic + "-" + partition + "-" + offset);
            }
        }, new FailureCallback() {
            //失败的回调
            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("发送消息失败1:" + throwable.getMessage());
            }
        });


        // 发送消息回调2
        kafkaTemplate.send(topic, data).addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("发送消息失败2："+throwable.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, String> result) {
                System.out.println("发送消息成功2：" + result.getRecordMetadata().topic() + "-"
                        + result.getRecordMetadata().partition() + "-" + result.getRecordMetadata().offset());
            }
        });

        // 声明事务
        kafkaTemplate.executeInTransaction(kafkaOperations -> {

            kafkaOperations.send(topic, data);
            throw new RuntimeException("fail");
        });

        return "success";
    }
}