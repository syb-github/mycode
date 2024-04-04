package com.bing.rabbitmqtest;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

import javax.annotation.Resource;

@EnableRabbit
@SpringBootApplication
@ServletComponentScan
@EnableAsync
public class RabbitmqTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(RabbitmqTestApplication.class, args);
    }

}
