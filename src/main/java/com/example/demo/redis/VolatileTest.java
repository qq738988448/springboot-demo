package com.example.demo.redis;

import java.util.concurrent.CountDownLatch;

public class VolatileTest {

    public static volatile int index = 0;

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(3);

        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(1000);
                for (int i = 0; i < 10000; i++) {
                    index++;
                    Thread.sleep(10);
                }
                countDownLatch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread thread1 = new Thread(() -> {
            try {
                Thread.sleep(1000);
                for (int i = 0; i < 10; i++) {
                    Thread.sleep(3);
                    System.out.println(index);
                }
                countDownLatch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread thread2 = new Thread(() -> {
            try {
                Thread.sleep(1000);
                for (int i = 0; i < 10; i++) {
                    Thread.sleep(5);
                    System.out.println(index);
                }
                countDownLatch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
        thread1.start();
        thread2.start();
        countDownLatch.await();

    }


}
