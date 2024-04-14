package com.bing.rabbitmqtest.middleware.listener;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/*
 * kafkaDemo
 */
@Component
public class KafKaListenner {

    @KafkaListener(topics = "topic1")
    public void listenT1(ConsumerRecord<?, ?> cr) throws Exception {
        System.out.println("listenT1收到消息！！   topic:>>>  " + cr.topic() + "    key:>>  " + cr.key() + "    value:>>  " + cr.value());
    }

    @KafkaListener(topics = "topic2")
    public void listenT2(ConsumerRecord<?, ?> cr) throws Exception {
        System.out.println("listenT2收到消息！！   topic:>>>  " + cr.topic() + "    key:>>  " + cr.key() + "    value:>>  " + cr.value());
    }

}
