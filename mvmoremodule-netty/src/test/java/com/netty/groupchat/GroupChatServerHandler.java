package com.netty.groupchat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GroupChatServerHandler extends SimpleChannelInboundHandler<String> {


    //定义一个channel组，用来管理所有的channel
    //GlobalEventExecutor.INSTANCE 全局事件执行器，是一个单例
    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    /***
     * 一旦连接上，第一个被执行
     * 将当前的channel加入到channelGroup
     * 并加连接上的消息推送到其他客户端
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        //对组里面的channel发送消息，无需自己遍历
        channelGroup.writeAndFlush("[客户端]" + channel.remoteAddress() + "加入聊天 [" + sdf.format(new Date()) + "]\n");
        channelGroup.add(channel);
    }


    /***
     * 这个方法表示channel处于活动状态
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress() + " 上线......");
    }

    /***
     * 表示channel下线
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress() + " 下线......");
    }

    /***
     * 客户端断开连接
     * 这个方法执行后channelGroup会自动移除对应的channel，我们不需要处理
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        //对组里面的channel发送消息，无需自己遍历
        channelGroup.writeAndFlush("[客户端]" + channel.remoteAddress() + "下线\n");
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        Channel channel = ctx.channel();
        //服务器接受当前channel的消息并转发到其他channel
        channelGroup.forEach(ch -> {
            if (ch != channel) {
                ch.writeAndFlush("[客户]" + channel.remoteAddress() + " 发送了消息:" + msg + "\n");
            } else {
                ch.writeAndFlush("[自己] 发送了消息:" + msg + "\n");
            }
        });
    }

    /***
     * 出现异常关闭通道
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
