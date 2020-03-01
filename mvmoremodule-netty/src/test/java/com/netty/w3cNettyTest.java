package com.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;
import org.junit.Test;

import java.util.Date;

public class w3cNettyTest {
    /***
     *
     */
    @Test
    public void discardServerTest() {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                                /***
                                 * 这里做的是收到消息后的处理 可以丢弃也可以做其他操作
                                 * @param ctx
                                 * @param msg
                                 * @throws Exception
                                 */
                                @Override
                                public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                    //discardMsg(ctx, msg);
                                    //receiveMsg(ctx, msg);
                                    answerMsg(ctx, msg);
                                }

                                @Override
                                public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
                                    // 当出现异常就关闭连接
                                    cause.printStackTrace();
                                    ctx.close();
                                }
                            });
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);
            // 绑定端口，开始接收进来的连接
            ChannelFuture f = b.bind(8000).sync();
            // 等待服务器  socket 关闭 。
            // 在这个例子中，这不会发生，但你可以优雅地关闭你的服务器。
            f.channel().closeFuture().sync();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

    /***
     * 丢弃消息
     * @param ctx
     * @param msg
     */
    public void discardMsg(ChannelHandlerContext ctx, Object msg) {
        System.out.println("=======收到消息并丢弃========");
        ((ByteBuf) msg).release();
    }

    /**
     * 接受消息
     *
     * @param ctx
     * @param msg
     */
    public void receiveMsg(ChannelHandlerContext ctx, Object msg) {
        System.out.println("======查看收到的消息=======");
        ByteBuf in = (ByteBuf) msg;
        System.out.println(in.toString(CharsetUtil.US_ASCII));
    }


    /***
     * 应答客户端
     * @param ctx
     * @param msg
     */
    public void answerMsg(ChannelHandlerContext ctx, Object msg) {
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello ~ 客户端1", CharsetUtil.UTF_8));
    }


    /***
     * 时间服务端
     */
    @Test
    public void timeServerTest() {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                                /***
                                 * channelActive() 方法将会在连接被建立并且准备进行通信时被调用
                                 * @param ctx
                                 * @throws Exception
                                 */
                                @Override
                                public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                    final ByteBuf time = ctx.alloc().buffer(4);
                                    time.writeInt((int) (System.currentTimeMillis() / 1000L + 2208988800L));
                                    final ChannelFuture f = ctx.writeAndFlush(time);
                                    f.addListener(new ChannelFutureListener() {
                                        @Override
                                        public void operationComplete(ChannelFuture channelFuture) throws Exception {
                                            System.out.println("=========关闭连接=========");
                                            ctx.close();
                                        }
                                    });
                                }

                                @Override
                                public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
                                    // 当出现异常就关闭连接
                                    cause.printStackTrace();
                                    ctx.close();
                                }
                            });
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);
            // 绑定端口，开始接收进来的连接
            ChannelFuture f = b.bind(8000).sync();
            // 等待服务器  socket 关闭 。
            // 在这个例子中，这不会发生，但你可以优雅地关闭你的服务器。
            f.channel().closeFuture().sync();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

    /***
     * 时间客户端端
     */
    @Test
    public void timeClientTest() {
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(workerGroup)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .handler(new ChannelInitializer<SocketChannel>() {

                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                                @Override
                                public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                    ByteBuf m = (ByteBuf) msg;
                                    long currentTimeMillis = (m.readUnsignedInt() - 2208988800L) * 1000L;
                                    System.out.println(new Date(currentTimeMillis));
                                    ctx.close();
                                    m.release();
                                }

                                @Override
                                public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
                                    cause.printStackTrace();
                                    ctx.close();
                                }
                            });
                        }
                    });
            // 启动客户端
            ChannelFuture f = b.connect("localhost", 8000).sync();
            // 等待连接关闭
            f.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            workerGroup.shutdownGracefully();
        }
    }


}
