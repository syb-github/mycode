package com.bing.rabbitmqtest.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMqConfig {
    public static final String DIRECTNAME= "direct";


    // 声明队列
    @Bean
    public Queue dirQueue(){
        return  new Queue("syb_queue");
    }

    @Bean
    public Queue dirQueue2(){
        return  new Queue("syb_queue2");
    }

    // 声明交换机
    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange("syb_directExchange");
    }


    // 声明交换机和队列的绑定关系
    @Bean
    public Binding bindingDirect(){
        return BindingBuilder.bind(dirQueue()).to(directExchange()).with("syb");
    }
    @Bean
    public Binding bindingDirect2(){
        return BindingBuilder.bind(dirQueue2()).to(directExchange()).with("syb2");
    }

    public void test(){
        System.out.println(123123);
    }

}
