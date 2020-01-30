package com.netty.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class NettyByteBuf01 {


    public static void main(String[] args) {
        /***
         * 1.创建对象，该对象包含一个数组arr,是一个byte[10]
         * 2.在netty的buffer中，不需要使用flip进行反转
         * 3.底层维护了readerindex 和 writerIndex
         * 4.通过 readerIndex 和 writerIndex 和 capacity , 将buffer分成三个区域
         * 0 -- > readerIndex 已经读取的区域
         * readerIndex -- > writerIndex 可读的区域
         * writerIndex -- capacity 可写的区域
         */
        ByteBuf byteBuf = Unpooled.buffer(10);
        for (int i = 0; i < 10; i++) {
            byteBuf.writeByte(i);
        }
        System.out.println("capacity=" + byteBuf.capacity());

        for (int i = 0; i < byteBuf.capacity(); i++) {
            System.out.println(byteBuf.getByte(i));
        }

        for (int i = 0; i < byteBuf.capacity(); i++) {
            System.out.println(byteBuf.readByte());
        }

    }
}
