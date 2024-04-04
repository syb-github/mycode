package com.bing.rabbitmqtest.middleware.service;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RedissonClient;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author sunyibing
 * @date 2024/3/25
 */
@Slf4j
@Service
public class RedisTest {
    // 本地线程局部缓存
    private static final ThreadLocal<String> THREAD_LOCAL = new ThreadLocal<>();
    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Resource
    private RedissonClient redissonClient;

    private static final DefaultRedisScript<Long> UNLOCK_SCRIPT;


    static {
        UNLOCK_SCRIPT = new DefaultRedisScript<>();
        UNLOCK_SCRIPT.setLocation(new ClassPathResource("lua/redis.lua"));
        UNLOCK_SCRIPT.setResultType(Long.class);
    }

//    Executor executor = new ThreadPoolExecutor();

    public void run() throws InterruptedException {
//        stringRedisTemplate.opsForValue().set("id", "22", 100, TimeUnit.SECONDS);
//        stringRedisTemplate.opsForHash().put("hash", "id", "22");
        // 1
//        // 获取订单号的唯一id
//        LocalDateTime now = LocalDateTime.now();
//        long threadId = Thread.currentThread().getId();
//        // key  业务：实体类：业务id
//        String prefix = "login:thread";
//        long nowSecond = System.currentTimeMillis();
//        // 生成序号
//        String nowStr = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//        // redis 自增长
//        long icr = stringRedisTemplate.opsForValue().increment(prefix + ":icr:"+nowStr);
//        long id = nowSecond << 32 | icr;
//        System.out.println(id);

        // 2
        // lua脚本处理redis数据操作的原子性  适用场景：释放分布式锁
//        stringRedisTemplate.execute(UNLOCK_SCRIPT, Collections.singletonList("name"), "111");


        // 3 基于Redis的分布式锁

//        if(stringRedisTemplate.opsForValue().setIfAbsent("name", "111")){
//            System.out.println(Thread.currentThread().getId()+"获取锁成功");
//        } else {
//            System.out.println(Thread.currentThread().getId()+"获取锁失败");
//        }

        // 4 基于redisson的可重入的分布式锁
//        RLock lock = redissonClient.getLock("name");
//        try {
////            if (lock.tryLock()) {
////                System.out.println(Thread.currentThread().getId() + "获取锁成功");
////                Thread.sleep(3000);
////            } else {
////                System.out.println(Thread.currentThread().getId() + "获取锁失败");
////            }
//            // 获取锁失败重试
//            while (!lock.tryLock()) {
//                System.out.println(Thread.currentThread().getId() + "获取锁失败");
//                Thread.sleep(1000);
//            }
//            System.out.println(Thread.currentThread().getId() + "获取锁成功");
//            Thread.sleep(3000);
//
//        } catch (Exception e) {
//            log.error(e.getMessage());
//        } finally {
//            lock.unlock();
//            System.out.println(Thread.currentThread().getId() + "释放锁成功");
//        }


        THREAD_LOCAL.set(Thread.currentThread().getId()+"测试线程局部缓存");
        Thread.sleep(3000);
        System.out.println(THREAD_LOCAL.get());
    }
}
