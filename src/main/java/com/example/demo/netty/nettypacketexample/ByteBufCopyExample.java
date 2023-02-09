package com.example.demo.netty.nettypacketexample;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;

public class ByteBufCopyExample {

    public static void main(String[] args) {
        ByteBuf buf= ByteBufAllocator.DEFAULT.buffer();
        buf.writeBytes(new byte[]{1,2,3,4,5,6,7,8,9,10});
        log(buf);
        //从buf这个总的数据中，分别拆分5个字节保存到两个ByteBuf中
        //零拷贝机制    (浅克隆)
        ByteBuf bb1=buf.slice(0,5);
        ByteBuf bb2=buf.slice(5,5);
        log(bb1);
        log(bb2);
        System.out.println("修改原始数据");
        buf.setByte(2,8);
        log(bb1);
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
