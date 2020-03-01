package com.netty.protocoltcp;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

/***
 * 解码器
 * 使用ReplayingDecoder 解决粘包和拆包
 */
public class MyMessageDecoder extends ReplayingDecoder<Void> {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        System.out.println("MyMessageDecoder decode 被调用");
        //将获取到的字节码转成MessageProtocol
        int length = byteBuf.readInt();
        byte[] content = new byte[length];
        byteBuf.readBytes(content);
        //封装成MessageProtocol对象 并放入到list中传给下一个handler
        MessageProtocol messageProtocol = new MessageProtocol(length,content);
        list.add(messageProtocol);
    }
}
