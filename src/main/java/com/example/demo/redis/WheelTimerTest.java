package com.example.demo.redis;

import io.netty.util.HashedWheelTimer;
import io.netty.util.Timeout;
import io.netty.util.TimerTask;
import io.netty.util.concurrent.DefaultThreadFactory;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 时间輪
 */
public class WheelTimerTest {
    public static HashedWheelTimer hashedWheelTimer = new HashedWheelTimer(new DefaultThreadFactory("demo-timer"), 100, TimeUnit.MILLISECONDS, 1024, true);


    public static void main(String[] args) {
        System.out.println("currentDate:" + new Date());
        hashedWheelTimer.newTimeout(new TestTimerTask("task1", 0), a[0], TimeUnit.SECONDS);
        System.out.println("主程序结束");
    }

    public static int[] a = {1, 1, 3, 3, 5};

    static class TestTimerTask implements TimerTask {

        private String name;

        private int index;

        public TestTimerTask(String name, int index) {
            this.name = name;
            this.index = index;
        }

        @Override
        public void run(Timeout timeout) throws Exception {
            System.out.println(this.name + new Date());
            int i = ++this.index;
            if(i > a.length -1){
                throw new RuntimeException("连续请求结束");
            }
            hashedWheelTimer.newTimeout(new TestTimerTask("task1", i), a[i], TimeUnit.SECONDS);
        }
    }

}
