package com.example.demo.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

public class ZookeeperLock {
    public static void main(String[] args) {

        CuratorFramework curatorFramework =
                CuratorFrameworkFactory.builder().
                        connectString("127.0.0.1:2181").
                        sessionTimeoutMs(5000).
                        retryPolicy(new ExponentialBackoffRetry
                                (1000, 3)).
                        connectionTimeoutMs(4000).build();
        curatorFramework.start(); //表示启动.

        /**
         * locks 表示命名空间
         * 锁的获取逻辑是放在zookeeper
         * 当前锁是跨进程可见
         */
        InterProcessMutex lock = new InterProcessMutex(curatorFramework, "/locks");
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "->尝试抢占锁");
                try {
                    lock.acquire();//抢占锁,没有抢到，则阻塞
                    System.out.println(Thread.currentThread().getName() + "->获取锁成功");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    Thread.sleep(4000);
                    lock.release(); //释放锁
                    System.out.println(Thread.currentThread().getName() + "->释放锁成功");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, "t-" + i).start();
        }

    }

}
