package com.bing.rabbitmqtest.middleware.controller;

import com.bing.rabbitmqtest.middleware.config.MQProvider.MqttProviderConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/mqtt")
public class MQTTSendController {
    @Autowired
    private MqttProviderConfig providerClient;

    @RequestMapping("/sendMessage")
    @ResponseBody
    public String sendMessage(int qos,boolean retained,String topic,String message){
        try {
            providerClient.publish(qos, retained, topic, message);
            return "发送成功";
        } catch (Exception e) {
            e.printStackTrace();
            return "发送失败";
        }
    }
}