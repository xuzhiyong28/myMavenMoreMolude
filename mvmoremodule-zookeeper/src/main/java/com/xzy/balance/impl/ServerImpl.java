package com.xzy.balance.impl;

import com.xzy.balance.server.RegistProvider;
import com.xzy.balance.server.Server;
import com.xzy.balance.server.ServerHandler;
import com.xzy.balance.server.ZooKeeperRegistContext;
import com.xzy.balance.model.ServerData;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.apache.curator.retry.ExponentialBackoffRetry;

/***
 * 服务器类
 * 用来启动服务器并注册到zk
 */
public class ServerImpl implements Server {

    private EventLoopGroup bossGroup = new NioEventLoopGroup();
    private EventLoopGroup workGroup = new NioEventLoopGroup();
    private ServerBootstrap bootStrap = new ServerBootstrap();
    private ChannelFuture cf;
    private String zkAddress;
    private String serversPath;
    private String currentServerPath;
    private ServerData sd;

    private volatile boolean binded = false;

    private CuratorFramework curatorFramework;
    private final RegistProvider registProvider;


    public ServerImpl(String zkAddress, String serversPath, ServerData serverData){
        this.registProvider = new DefaultRegistProvider();
        this.zkAddress = zkAddress;
        this.serversPath = serversPath;
        this.sd = serverData;
        this.curatorFramework = CuratorFrameworkFactory.builder()
                .connectString(this.zkAddress)
                .sessionTimeoutMs(5000)
                .connectionTimeoutMs(5000)
                .retryPolicy(new ExponentialBackoffRetry(1000,3))
                .build();
        this.curatorFramework.getConnectionStateListenable().addListener(new ConnectionStateListener() {
            @Override
            public void stateChanged(CuratorFramework curatorFramework, ConnectionState connectionState) {
                if (connectionState == ConnectionState.LOST) {
                    System.out.println("连接丢失");//连接丢失
                } else if (connectionState == ConnectionState.CONNECTED) {
                    System.out.println("成功连接");
                } else if (connectionState == ConnectionState.RECONNECTED) {
                    System.out.println("重连成功");
                }
            }
        });
        this.curatorFramework.start();
    }

    //初始化服务端
    private void initRuning() throws Exception{

        String mePath = serversPath.concat("/").concat(sd.getPort().toString()); //  /servers/6001
        //注册到zk
        registProvider.regist(new ZooKeeperRegistContext(mePath , this.curatorFramework , this.sd));
        currentServerPath = mePath;
    }

    @Override
    public void bind() {
        if(binded){
            return;
        }
        System.out.println(sd.getPort() + ":binding...");
        try{
            initRuning();
        }catch (Exception e){
            System.out.println("初始化服务器错误:" + e.getMessage());
        }
        bootStrap.group(bossGroup,workGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 1024)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline p = ch.pipeline();
                        p.addLast(new ServerHandler(new DefaultBalanceUpdateProvider(currentServerPath,curatorFramework)));
                    }
                });
        try{
            cf =  bootStrap.bind(sd.getPort()).sync();
            binded = true;
            System.out.println(sd.getPort() + ":binded...");
            cf.channel().closeFuture().sync();
        }catch (InterruptedException e){
            System.out.println("InterruptedException :" + e.getMessage());
        }finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }


    public EventLoopGroup getBossGroup() {
        return bossGroup;
    }

    public void setBossGroup(EventLoopGroup bossGroup) {
        this.bossGroup = bossGroup;
    }

    public EventLoopGroup getWorkGroup() {
        return workGroup;
    }

    public void setWorkGroup(EventLoopGroup workGroup) {
        this.workGroup = workGroup;
    }

    public ServerBootstrap getBootStrap() {
        return bootStrap;
    }

    public void setBootStrap(ServerBootstrap bootStrap) {
        this.bootStrap = bootStrap;
    }

    public ChannelFuture getCf() {
        return cf;
    }

    public void setCf(ChannelFuture cf) {
        this.cf = cf;
    }

    public String getZkAddress() {
        return zkAddress;
    }

    public void setZkAddress(String zkAddress) {
        this.zkAddress = zkAddress;
    }

    public String getServersPath() {
        return serversPath;
    }

    public void setServersPath(String serversPath) {
        this.serversPath = serversPath;
    }

    public String getCurrentServerPath() {
        return currentServerPath;
    }

    public void setCurrentServerPath(String currentServerPath) {
        this.currentServerPath = currentServerPath;
    }

    public ServerData getSd() {
        return sd;
    }

    public void setSd(ServerData sd) {
        this.sd = sd;
    }

    public CuratorFramework getCuratorFramework() {
        return curatorFramework;
    }

    public void setCuratorFramework(CuratorFramework curatorFramework) {
        this.curatorFramework = curatorFramework;
    }

    public RegistProvider getRegistProvider() {
        return registProvider;
    }
}
