package com.bing.rabbitmqtest.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author sunyibing
 * @date 2024/4/2
 */
@Service
public class LockServiceImpl {

    private static final Lock LOCK = new ReentrantLock();

    private static final ThreadLocal<String> THREAD_LOCAL = new ThreadLocal<>();

    synchronized void aa () {

    }

    public void run () throws InterruptedException {

        // 获取锁
        if(LOCK.tryLock()){
            System.out.println(Thread.currentThread()+"获取锁10s");
            Thread.sleep(10000);
            // 中断锁
            LOCK.lockInterruptibly();
            // 释放锁  finally中释放锁
            LOCK.unlock();
            System.out.println(Thread.currentThread()+"释放锁");
        } else {
            System.out.println(Thread.currentThread()+"获取锁失败");
        }
    }


}
