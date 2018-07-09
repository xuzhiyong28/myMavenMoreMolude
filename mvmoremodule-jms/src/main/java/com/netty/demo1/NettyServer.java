package com.netty.demo1;/**
 * Created by Administrator on 2018-07-02.
 */

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;

/**
 * @author xuzhiyong
 * @createDate 2018-07-02-14:48
 * netty 服务端
 * boos--主要负责创建连接，
 * worker--主要负责处理每一条连接的数据读写的线程组
 */
public class NettyServer {
    public static void main(String agrs[]){
        ServerBootstrap serverBootstrap = new ServerBootstrap(); //引导类，引导我们进行服务端的启动工作
        NioEventLoopGroup boos = new NioEventLoopGroup(); //IOServer.java中的接受新连接线程，主要负责创建新连接
        NioEventLoopGroup worker = new NioEventLoopGroup(); //IOClient.java中的负责读取数据的线程，主要用于读取数据以及业务逻辑处理
        serverBootstrap.group(boos,worker)
                       .childOption(ChannelOption.SO_KEEPALIVE, true) //是否开启TCP底层心跳机制，true为开启
                       .childOption(ChannelOption.SO_REUSEADDR, true) //端口释放后立即就可以被再次使用
                       .childOption(ChannelOption.TCP_NODELAY, true)  //是否开始Nagle算法
                       .channel(NioServerSocketChannel.class) //指定IO模型为NIO模型
                       .childHandler(new ChannelInitializer<NioSocketChannel>(){ //创建一个通道初始化器,负责每条连接的数据读写
                           @Override
                           protected void initChannel(NioSocketChannel ch) throws Exception {
                                ch.pipeline().addLast(new StringDecoder());
                                ch.pipeline().addLast(new SimpleChannelInboundHandler<String>(){
                                    @Override
                                    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String msg) throws Exception {
                                        System.out.println(msg);
                                    }
                                });
                           }
                       }).bind(8000);

    }
}
