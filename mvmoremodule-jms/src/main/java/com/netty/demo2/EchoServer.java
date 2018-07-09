package com.netty.demo2;/**
 * Created by Administrator on 2018-07-03.
 */

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 * @author xuzhiyong
 * @createDate 2018-07-03-14:40
 */
public class EchoServer {
    private final int port;
    public EchoServer(int port){
        this.port = port;
    }

    public static void main(String agrs[]){
        new EchoServer(8000).start();
    }

    public void start(){
        final EchoServerHandler echoServerHandler = new EchoServerHandler();
        NioEventLoopGroup group = new NioEventLoopGroup();
        try{
            ServerBootstrap b = new ServerBootstrap();
            b.group(group).channel(NioServerSocketChannel.class)
                          .localAddress(new InetSocketAddress(port))
                          .childHandler(new ChannelInitializer<SocketChannel>() {
                              @Override
                              protected void initChannel(SocketChannel ch) throws Exception {
                                    ch.pipeline().addLast(echoServerHandler);
                              }
                          });
            ChannelFuture f = b.bind().sync();
            f.channel().closeFuture().sync();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                group.shutdownGracefully().sync();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
