package com.example.demo.netty.nettypacketexample;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.ByteBufUtil;

public class NettyByteBufExample {

    public static void main(String[] args) {
        ByteBuf buf=ByteBufAllocator.DEFAULT.buffer(); //构建一个ByteBuf
        System.out.println("=======before ======");
        log(buf);
        //构建一个字符串
        StringBuilder stringBuilder=new StringBuilder();
        for (int i = 0; i < 400; i++) {
            stringBuilder.append("-"+i);
        }
        buf.writeBytes(stringBuilder.toString().getBytes());
        System.out.println("=======after ======");
        buf.readShort(); //读取2个字节
        buf.readByte(); //读取一个字节

        log(buf);
    }
    private static void log(ByteBuf buf){
        StringBuilder sb=new StringBuilder();
        sb.append(" read index:").append(buf.readerIndex());  //读索引
        sb.append(" write index:").append(buf.writerIndex()); //写索引
        sb.append(" capacity :").append(buf.capacity()) ; //容量
        ByteBufUtil.appendPrettyHexDump(sb,buf);
        System.out.println(sb.toString());
    }
}
