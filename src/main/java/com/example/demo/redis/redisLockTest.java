package com.example.demo.redis;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class redisLockTest {

    private static RedissonClient redissonClient;

    static {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://39.99.253.2:6379").setPassword("pgy@123456");
        redissonClient = Redisson.create(config);
    }


    public static void main(String[] args) throws IOException {
        RLock rLock = redissonClient.getLock("updateOrder");

        Thread thread1 = new Thread(()->{
            try {
                if (rLock.tryLock(100, 10, TimeUnit.SECONDS)) {
                    System.out.println("thread1获取锁成功");
                    Thread.sleep(2000);
                    rLock.unlock();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread thread2 = new Thread(()->{
            try {
                if (rLock.tryLock(100, 10, TimeUnit.SECONDS)) {
                    System.out.println("thread2获取锁成功");
                    rLock.unlock();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread1.start();
        thread2.start();
        System.in.read();
        redissonClient.shutdown();
    }

}
