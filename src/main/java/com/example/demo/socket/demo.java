package com.example.demo.socket;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class demo {

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        boolean b = lock.tryLock();


    }
}
