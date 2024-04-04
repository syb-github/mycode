package com.bing.rabbitmqtest.controller;

import com.bing.rabbitmqtest.service.MessageRabbitMQImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
public class TestContoller {

    @Resource
    MessageRabbitMQImpl messageRabbitMQ;
    @GetMapping("/test")
    public void test(String text){
        messageRabbitMQ.sendMessage(text);
    }

    public static void main(String[] args) {
        List<String> aa = new ArrayList<>();
        aa.add("6");
        aa.add("2");
        aa.add("7");
        aa.add("3");
        // 冒泡排序
        for (int i = 0; i < aa.size(); i++) {
            for (int j = 0; j < aa.size() - 1; j++) {
                if (Integer.parseInt(aa.get(j)) > Integer.parseInt(aa.get(j + 1))) {
                    String temp = aa.get(j);
                    aa.set(j, aa.get(j + 1));
                    aa.set(j + 1, temp);
                }
            }
        }
        // 选择排序
        for (int i = 0; i < aa.size(); i++) {
            int min = i;
            for (int j = i + 1; j < aa.size(); j++) {
                if (Integer.parseInt(aa.get(j)) < Integer.parseInt(aa.get(min))) {
                    min = j;
                }
            }
            String temp = aa.get(i);
            aa.set(i, aa.get(min));
            aa.set(min, temp);
        }
        //插入排序
        for (int i = 1; i < aa.size(); i++) {
            String temp = aa.get(i);
            int j = i - 1;
            while (j >= 0 && Integer.parseInt(aa.get(j)) > Integer.parseInt(temp)) {
                aa.set(j + 1, aa.get(j));
                j--;
            }
            aa.set(j + 1, temp);
        }

    }
}
