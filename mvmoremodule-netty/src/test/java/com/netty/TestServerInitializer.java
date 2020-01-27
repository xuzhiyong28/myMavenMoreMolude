package com.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.net.URI;

/**
 * @author xuzhiyong
 * @createDate 2019-12-30-20:58
 */
public class TestServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        //向管道加入处理器
        ChannelPipeline pipeline = socketChannel.pipeline();
        //加入一个netty提供的httpServerCodec
        //HttpServerCodec netty提供的一个处理http的编码解码器
        pipeline.addLast("MyHttpServerCodec", new HttpServerCodec());
        //添加自定义的处理器 SimpleChannelInboundHandler是ChannelInboundHandlerAdapter的子类。
        // HttpObject表示客户端和服务器端相互通信的数据被封装成HttpObject
        pipeline.addLast("MySimpleChannelInboundHandler",new SimpleChannelInboundHandler<HttpObject>() {
            /****
             * 读取客户端数据
             * @throws Exception
             */
            @Override
            protected void channelRead0(ChannelHandlerContext cxt, HttpObject msg) throws Exception {
                if (msg instanceof HttpRequest) {
                    System.out.println("msg的类型:" + msg.getClass());
                    System.out.println("客户端地址:" + cxt.channel().remoteAddress());
                    System.out.println("对应的channel=" + cxt.channel() + " pipeline=" + cxt.pipeline()
                    + "通过pipeline获取channel " + cxt.pipeline().channel());
                    System.out.println("当前cxt的handler=" + cxt.handler());
                    HttpRequest httpRequest = (HttpRequest) msg;
                    URI uri = new URI(httpRequest.uri());
                    if("/favicon.ico".equals(uri.getPath())){
                        return ;
                    }


                    //回复给浏览器信息[http协议]
                    ByteBuf byteBuf = Unpooled.copiedBuffer("hello,我是服务器", CharsetUtil.UTF_8);
                    //构建一个http的响应
                    DefaultFullHttpResponse defaultFullHttpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, byteBuf);
                    defaultFullHttpResponse.headers().set(HttpHeaderNames.CONTENT_TYPE,"text/plain");
                    defaultFullHttpResponse.headers().set(HttpHeaderNames.CONTENT_LENGTH,byteBuf.readableBytes());
                    cxt.writeAndFlush(defaultFullHttpResponse);
                }
            }
        });

    }
}
