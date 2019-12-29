package com.nio;

import jdk.management.resource.internal.inst.FileChannelImplRMHooks;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;
import java.util.function.Consumer;

/**
 * @author xuzhiyong
 * @createDate 2019-12-17-22:23
 */
public class Test {
    @org.junit.Test
    public void testBuffer() {
        //Buffer的测试
        //创建一个buffer，大小为5 ，就是可以存5个int
        IntBuffer intBuffer = IntBuffer.allocate(5);
        //往buffer存放数据
        for (int i = 0; i < intBuffer.capacity(); i++) {
            intBuffer.put(i * 2);
        }
        //取数据

        intBuffer.flip(); //读写切换，读写之间需要切换   **重要**

        while (intBuffer.hasRemaining()) {
            //get里面有维护一个索引，每次get一次索引会往下移动
            System.out.println(intBuffer.get());
        }
    }

    @org.junit.Test
    public void nioFileChannelTest() throws IOException {
        String str = "hello,xuzy";
        FileOutputStream fileOutputStream = new FileOutputStream("d:\\file01.txt");
        FileChannel fileChannel = fileOutputStream.getChannel();
        //创建一个缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        //数据放入buffer
        byteBuffer.put(str.getBytes());
        //对bytebuffer进行flip
        byteBuffer.flip();
        //将bytebuffer数据写入到filechannel
        fileChannel.write(byteBuffer);
        fileOutputStream.close();
    }

    @org.junit.Test
    public void nioFileChannelTest2() throws IOException {
        File file = new File("d:\\file01.txt");
        FileInputStream fileInputStream = new FileInputStream(file);
        //获得输入流的channel
        FileChannel fileChannel = fileInputStream.getChannel();
        //创建缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate((int) file.length());
        //将通道的数据读入到buffer
        fileChannel.read(byteBuffer);
        //将缓冲区的字节转成字符串
        System.out.println(new String(byteBuffer.array()));
        fileInputStream.close();
    }

    /***
     * 使用一个buffer完成文件读和取
     */
    @org.junit.Test
    public void nioFileChannelTest3() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(new File("d:\\file01.txt"));
        FileChannel fileChannel1 = fileInputStream.getChannel();

        FileOutputStream fileOutputStream = new FileOutputStream(new File("d:\\file02.txt"));
        FileChannel fileChannel2 = fileOutputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(512);

        while (true) {
            //每次循环都清空下byteBuffer
            byteBuffer.clear();
            int read = fileChannel1.read(byteBuffer);
            if (read == -1) {
                break;
            }
            //将buffer中的数据写入到fileChannel2
            byteBuffer.flip();
            fileChannel2.write(byteBuffer);
        }
        fileInputStream.close();
        fileOutputStream.close();
    }


    /***
     * 程序说明
     * 1.当客户端连接时，会通过ServerSocketChannel得到SocketChannel
     * 2.seletor开始监听
     * 3.将SocketChannel注册到selector,一个selector上可以注册多个SocketChannel
     * 4.注册后返回一个SelectionKey，会和该selector关联
     * 5.selector进行监听select方法，返回有事件发生的通道个数
     * 6.进一步得到各个SelectionKey
     * 7.再通过SelectionKey反向获取SocketChannel，方法channel()
     * 可以通过得到的channel完成业务处理
     *
     *
     *
     * @throws IOException
     */

    @org.junit.Test
    public void NioServerTest() throws IOException {
        //创建ServerSocketChannel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //得到一个Selector对象
        Selector selector = Selector.open();
        //绑定一个端口，在服务器端监听
        serverSocketChannel.socket().bind(new InetSocketAddress(6666));
        //设置为非阻塞
        serverSocketChannel.configureBlocking(false);
        //把ServerSocketChannel注册到selector上，关心的事件是OP_ACCEPT(连接事件)
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        //循环等待客户端连接
        while (true) {

            if (selector.select(1000) == 0) { //没有事件发生
                System.out.println("========服务器等待1s,无连接=======");
                continue;
            }
            //如果返回的是大于0的话,就获取到相关的SelectionKey集合
            //1.如果返回>0,表示已经获取到关注的事件
            //2.selectionKeys就是关注的事件的集合
            //3.通过selectionKeys就可以获取到相关的通道
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                //根据key对应的通道对应的事件
                if (key.isAcceptable()) { //如果是OP_ACCEPT事件，其实就是有客户端连接的话
                    //该客户端生成一个SoecktChannel
                    try {
                        SocketChannel socketChannel = serverSocketChannel.accept();
                        socketChannel.configureBlocking(false);
                        //将socketChannel注册到selector上,关注的事件是读，同时给socketChannel关联一个buffer
                        socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                //如果事件是一个读事件
                if (key.isReadable()) {
                    //通过key反向获取到对应的channel
                    SocketChannel channel = (SocketChannel) key.channel();
                    //获取到通过关联的buffer
                    ByteBuffer buffer = (ByteBuffer) key.attachment();
                    try {
                        channel.read(buffer);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println("客户端发过来的消息是:" + new String(buffer.array()));
                }

                //删除selectionKey,防止重复操作
                iterator.remove();

            }
        }

    }


    @org.junit.Test
    public void NioClientTest() throws IOException {
        //得到一个网络通道
        SocketChannel socketChannel = SocketChannel.open();
        //设置非阻塞模式
        socketChannel.configureBlocking(false);
        //提供服务器端的IP和端口
        InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1", 6666);
        //连接服务器
        if (!socketChannel.connect(inetSocketAddress)) {
            while (!socketChannel.finishConnect()){
                System.out.println("因为连接需要时间，客户端不会阻塞，可以做其他工作");
            }
        }

        //如果连接成功
        String str = "hello";
        ByteBuffer buffer = ByteBuffer.wrap(str.getBytes());
        //发送数据
        socketChannel.write(buffer);

    }
}
