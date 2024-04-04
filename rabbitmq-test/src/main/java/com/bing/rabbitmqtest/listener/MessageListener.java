package com.bing.rabbitmqtest.listener;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class MessageListener {
    @RabbitListener(queues = "syb_queue")
    public void receive(String id){
        System.out.println("完成syb_queue,id:"+id);
    }

    @RabbitListener(queues = "syb_queue2")
    public void receive2(String id, Message message, Channel channel) throws IOException {
        System.out.println("完成syb_queue2,id:"+id);
        System.out.println(message.toString());
//        channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
    }
}
