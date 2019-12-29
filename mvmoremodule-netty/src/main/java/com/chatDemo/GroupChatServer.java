package com.chatDemo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

/**
 * @author xuzhiyong
 * @createDate 2019-12-28-16:56
 */
public class GroupChatServer {

    private Selector selector;
    private ServerSocketChannel listenChannel;
    private static final int PORT = 6667;

    //初始化任务
    public GroupChatServer() throws IOException {
        //设置选择器
        selector = Selector.open();
        listenChannel = ServerSocketChannel.open();
        listenChannel.socket().bind(new InetSocketAddress(PORT));
        //设置非阻塞
        listenChannel.configureBlocking(false);
        //将该listenChannel注册到selector上
        listenChannel.register(selector, SelectionKey.OP_ACCEPT);
    }

    /***
     * 监听客户端连接
     */
    public void listen() throws IOException {
        while (true) {
            int count = selector.select();
            if (count > 0) {
                //有事件要处理
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    if (key.isAcceptable()) {
                        //如果是连接的事件
                        SocketChannel sc = listenChannel.accept();
                        sc.configureBlocking(false);
                        //将该连接注册到selector
                        sc.register(selector, SelectionKey.OP_READ);
                        System.out.println(sc.getRemoteAddress() + " 上线");
                    }

                    if (key.isReadable()) {
                        //如果是读事件
                        readData(key);
                    }

                    iterator.remove();
                }
            } else {
                System.out.println("====没有客户端连接，继续等待====");
            }
        }
    }

    /***
     * 读取客户端消息
     * @param key
     */
    public void readData(SelectionKey key) {
        SocketChannel channel = null;
        try {
            channel = (SocketChannel) key.channel();
            //创建Buffer
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            int count = channel.read(buffer);
            if (count > 0) {
                String msg = new String(buffer.array());
                System.out.println("from 客户端 " + msg);

                //服务端收到消息后，专门写一个方法来处理转发
                sendInfoToOtherClients(msg, channel);
            }

        } catch (IOException e) {
            try {
                System.out.println(channel.getRemoteAddress() + " 离线了");
                //取消注册
                key.cancel();
                //关闭通道
                channel.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    /***
     *  转发消息给其他客户端
     * @param msg
     * @param self 自己不需要转发
     */
    public void sendInfoToOtherClients(String msg, SocketChannel self) throws IOException {
        //从选择器里面取出当前的通道
        for (SelectionKey key : selector.keys()) {
            Channel tarageChannel = key.channel();
            //排除自己
            if (tarageChannel instanceof SocketChannel && tarageChannel != self) {
                SocketChannel dest = (SocketChannel) tarageChannel;
                ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes());
                //将buffer的数据写到通道中
                dest.write(buffer);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        GroupChatServer groupChatServer = new GroupChatServer();
        groupChatServer.listen();
    }


}
