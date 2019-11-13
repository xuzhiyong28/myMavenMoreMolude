package com.xzy.balance.impl;

import com.xzy.balance.client.BalanceProvider;
import com.xzy.balance.client.Client;
import com.xzy.balance.client.ClientHandler;
import com.xzy.balance.model.ServerData;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class ClientImpl implements Client {

    private EventLoopGroup group = null;
    private Channel channel = null;

    //负载均衡算法提供器
    private final BalanceProvider<ServerData> provider;

    public ClientImpl(BalanceProvider<ServerData> provider) {
        this.provider = provider;
    }

    @Override
    public void connect() throws Exception {
        try{
            //获取到负载最小的服务器
            ServerData serverData = provider.getBalanceItem();
            System.out.println("connecting to " + serverData.getHost() + ":" + serverData.getPort() + ", it's balance:" + serverData.getBalance());
            group = new NioEventLoopGroup();
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline p = ch.pipeline();
                            p.addLast(new ClientHandler());
                        }
                    });
            ChannelFuture f = b.connect(serverData.getHost(),serverData.getPort()).syncUninterruptibly();
            channel = f.channel();
            System.out.println("started success!");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void disConnect() throws Exception {
        try{

            if (channel!=null){
                channel.close().syncUninterruptibly();
            }
            group.shutdownGracefully();
            group = null;
            System.out.println("disconnected!");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public BalanceProvider<ServerData> getProvider() {
        return provider;
    }
}
