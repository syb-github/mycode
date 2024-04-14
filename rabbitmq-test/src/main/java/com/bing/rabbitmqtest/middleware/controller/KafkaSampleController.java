package com.bing.rabbitmqtest.middleware.controller;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author
 */ /*
 * kafkaDemo

 */
@RestController
@RequestMapping(name = "", path = "/kafka")
public class KafkaSampleController {
    @Resource
    private KafkaTemplate<String, String> template;

    @GetMapping(path = "/send")
    public String send(String topic, String key, String data) {
        template.send(topic, key, data);
        return "success";
    }
}