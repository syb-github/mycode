package com.bing.rabbitmqtest.middleware.listener;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.KafkaListeners;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import java.util.List;

/*
 * kafkaDemo
 */
@Component
public class KafKaListenner {

    /**
     *  @SendTo可用于消息转发
     * @param cr
     * @throws Exception
     */
    @KafkaListener(topics = "topic1")
    @SendTo(value = "topic2")
    public void listenT1(ConsumerRecord<?, ?> cr) throws Exception {
        System.out.println("listenT1收到消息！！   topic:>>>  " + cr.topic() + "    key:>>  " + cr.key() + "    value:>>  " + cr.value());
    }

    @KafkaListener(topics = "topic2")
    public void listenT2(ConsumerRecord<?, ?> cr, Acknowledgment ack) throws Exception {
        System.out.println("listenT2收到消息！！   topic:>>>  " + cr.topic() + "    key:>>  " + cr.key() + "    value:>>  " + cr.value());
        ack.acknowledge();
    }


    //批量消费
//    @KafkaListener(id = "consumer2", topics = {"sb_topic"}, groupId = "sb_group")
//    public void onBatchMessage(List<ConsumerRecord<String, Object>> records) {
//        System.out.println(">>> 批量消费一次，recoreds.size()=" + records.size());
//        for (ConsumerRecord<String, Object> record : records) {
//            System.out.println(record.value());
//        }
//    }

//    @KafkaListener(topics = "topicList")
    public void onBatchMessage(List<ConsumerRecord<String, String>> records){
        System.out.println(">>> 批量消费一次，recoreds.size()=" + records.size());
        for (ConsumerRecord<String, String> record : records) {
            System.out.println(record.value());
        }

    }
}
