package com.netty;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import java.util.concurrent.TimeUnit;

/**
 * @author xuzhiyong
 * @createDate 2019-12-29-12:57
 * 定义一个处理器
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {


    /****
     * 读取客户端数据
     * @param ctx 上下文对象，含有pipeline
     * @param msg 客户端传过来的消息
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //ByteBuf 是netty提供的，不是NIO的ByteBuffer
        //ByteBuf byteBuf = (ByteBuf)msg;
        //System.out.println("客户端发送的消息 ：" + byteBuf.toString(CharsetUtil.UTF_8));
        //System.out.println("客户端地址 ：" + ctx.channel().remoteAddress());

        //上面的如果执行很长时间会阻塞，所以可以用异步队列
        ctx.channel().eventLoop().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(10);
                    ctx.writeAndFlush(Unpooled.copiedBuffer("hello ~ 客户端1",CharsetUtil.UTF_8));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });


    }

    /***
     * 读取数据完毕后处理的事件
     * 这里读取完客户端数据后回复信息
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {

        //write + flush
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello ~ 客户端2",CharsetUtil.UTF_8));
    }

    /***
     * 处理异常，一般需要关闭通道
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }


}
