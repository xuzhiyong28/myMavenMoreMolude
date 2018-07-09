package com.netty.demo1;/**
 * Created by Administrator on 2018-07-02.
 */

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;

import java.util.Date;

/**
 * @author xuzhiyong
 * @createDate 2018-07-02-15:23
 */
public class NettyClient {
    public static void main(String agrs[]) throws InterruptedException {
        Bootstrap bootstrap = new Bootstrap();
        NioEventLoopGroup group = new NioEventLoopGroup();
        bootstrap.group(group)
                 .channel(NioSocketChannel.class)
                 .handler(new ChannelInitializer<Channel>(){
                     @Override
                     protected void initChannel(Channel ch) throws Exception {
                         ch.pipeline().addLast(new StringEncoder());
                     }
                 });
        Channel channel = bootstrap.connect("127.0.0.1",8000).channel();
        while(true){
            channel.writeAndFlush(new Date() + ": hello world!");
            Thread.sleep(2000);
        }
    }
}
