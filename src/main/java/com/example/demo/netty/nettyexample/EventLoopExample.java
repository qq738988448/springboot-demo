package com.example.demo.netty.nettyexample;

import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoop;
import io.netty.channel.nio.NioEventLoopGroup;

import java.net.ServerSocket;

public class EventLoopExample {

    public static void main(String[] args) {
        EventLoopGroup group=new NioEventLoopGroup(2);
//        group.register(); //把某个channel注册到某一个EventLoop的Selector
        NioEventLoop eventExecutors=(NioEventLoop)group.next();
        ServerSocket ss=null;
//        System.out.println(group.next().register(ss));
        System.out.println(group.next());

        group.next().submit(()->{
            System.out.println(Thread.currentThread().getName()+"----");
        });
    }
}
