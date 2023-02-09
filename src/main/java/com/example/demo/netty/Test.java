package com.example.demo.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;

public class Test {

    public static void main(String[] args) {
        ByteBuf header = ByteBufAllocator.DEFAULT.buffer();
        header.writeBytes(new byte[]{1,2,3,4,5});
        ByteBuf body = ByteBufAllocator.DEFAULT.buffer();
        body.writeBytes(new byte[]{6,7,8,9,10});
        CompositeByteBuf compositeByteBuf= Unpooled.compositeBuffer();
        compositeByteBuf.addComponents(true,header,body);
        log(compositeByteBuf);
        body.setByte(0,11);
        log(compositeByteBuf);

    }

    public static void log(ByteBuf buf){
        StringBuilder sb = new StringBuilder();
        ByteBufUtil.appendPrettyHexDump(sb,buf);
        System.out.println(sb.toString());
    }
}
