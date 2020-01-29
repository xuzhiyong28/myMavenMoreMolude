package com.netty.inbound;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/***
 * 自定义解码器
 */
public class MyByteToLongDecoder extends ByteToMessageDecoder {

    /***
     *
     * @param channelHandlerContext
     * @param byteBuf 入站的ByteBuf
     * @param list List集合，将解码后的数据传给下一个handler
     * @throws Exception
     */
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List list) throws Exception {
        // 因为 long -> 8个字节 才能读取一个Long
        if(byteBuf.readableBytes() >= 8){
            list.add(byteBuf.readLong());
        }
    }
}
