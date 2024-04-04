package com.bing.rabbitmqtest.service;

import com.bing.rabbitmqtest.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 阻塞队列实现
 * @author sunyibing
 * @date 2024/4/2
 */
@Service
@Slf4j
public class BlockQueueServiceImpl {
    // 创建一个队列
    private final BlockingQueue<User> queue = new ArrayBlockingQueue<>(1024*1024);

    // 创建一个单线程
    public static final ExecutorService executorService = Executors.newSingleThreadExecutor();

    @PostConstruct
    public void init() {
        executorService.submit(new Consumer());
    }

    // 创建一个内部类实现Runnable接口
    private class Consumer implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    User user = queue.take();
                    System.out.println("消费队列消息：" + user.getName());
                } catch (InterruptedException e) {
                    log.error(e.getMessage());
                }
            }
        }
    }

    /**
     * 将用户信息插入到队列中
     * @param user  用户信息
     */
    public void put(User user) {
        try {
            queue.put(user);
        } catch (InterruptedException e) {
            log.error(e.getMessage());
        }
    }
}
