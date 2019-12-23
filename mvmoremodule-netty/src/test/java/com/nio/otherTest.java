package com.nio;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;
import java.util.function.Function;

public class otherTest {
    @Test
    public void test() throws Exception {
        FileInputStream fileInputStream = new FileInputStream("D:\\1.txt");
        FileChannel fileChannelIn = fileInputStream.getChannel();

        FileOutputStream fileOutputStream = new FileOutputStream("D:\\2.txt");
        FileChannel fileChannelOut = fileOutputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(512);

        while (true) {
            byteBuffer.clear();
            int read = fileChannelIn.read(byteBuffer);
            if (read == -1) {
                break;
            }
            byteBuffer.flip();
            fileChannelOut.write(byteBuffer);
        }
        fileInputStream.close();
        fileOutputStream.close();
    }

    @Test
    public void test2() throws Exception {
        FileInputStream fileInputStream = new FileInputStream("D:\\1.txt");
        FileChannel fileChannelIn = fileInputStream.getChannel();

        FileOutputStream fileOutputStream = new FileOutputStream("D:\\2.txt");
        FileChannel fileChannelOut = fileOutputStream.getChannel();

        //使用transferFrom进行拷贝
        fileChannelIn.transferFrom(fileChannelOut, 0, fileChannelIn.size());

        fileInputStream.close();
        fileOutputStream.close();
    }

    @Test
    public void test3() {
        ByteBuffer buffer = ByteBuffer.allocate(64);

        //类型化方式放入数据
        buffer.putInt(100);
        buffer.putLong(9);
        buffer.putChar('许');

        //取出 -- 必须按照插入的顺序
        buffer.flip(); //进行翻转
        System.out.println(buffer.getInt());
        System.out.println(buffer.getLong());
        System.out.println(buffer.getChar());
    }

    @Test
    public void test4() {
        ByteBuffer buffer = ByteBuffer.allocate(64);
        for (int i = 0; i < 64; i++) {
            buffer.put((byte) i);
        }

        buffer.flip();

        //得到一个只读的Buffer
        ByteBuffer readOnlyBuffer = buffer.asReadOnlyBuffer();
        while (readOnlyBuffer.hasRemaining()) {
            System.out.println(readOnlyBuffer.get());
        }
    }

    /***
     * 直接将文件读到内存进行修改
     * 好处是直接在堆外内存修改，性能很高
     * @throws Exception
     */
    @Test
    public void test5() throws Exception {
        //MappedByteBuffer 可以让文件直接在内存(堆外内存)修改，操作系统不需要拷贝一次
        RandomAccessFile randomAccessFile = new RandomAccessFile("D:\\1.txt", "rw");
        FileChannel fileChannel = randomAccessFile.getChannel();
        /***
         * 参数1，FileChannel.MapMode.READ_WRITE : 读写模式
         * 参数2 ： 起始位置。可以直接修改的起始位置
         * 参数3 ： 映射到内存的大小，即将1.txt的多少个字节映射到内存
         * 意思是可以修改的范围是0-5
         */
        MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, 5);
        mappedByteBuffer.put(0, (byte) 'H');
        mappedByteBuffer.put(3, (byte) '9');
        randomAccessFile.close();
        System.out.println("====修改成功====");
    }

    @Test
    public void test6() throws IOException {
        //Scattering： 将数据写入到buffer时，可以采用buffer数组，依次写入
        //Gathering ： 从buffer读取数据时，可以采用buffer数组，依次读

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress inetSocketAddress = new InetSocketAddress(7000);

        //绑定端口到socket，并启动
        serverSocketChannel.socket().bind(inetSocketAddress);

        //创建buffer数组
        ByteBuffer[] byteBuffers = new ByteBuffer[2];
        byteBuffers[0] = ByteBuffer.allocate(5);
        byteBuffers[1] = ByteBuffer.allocate(3);

        //监听客户端请求
        SocketChannel socketChannel = serverSocketChannel.accept();
        int messageLength = 8;
        //循环读取
        while (true) {
            int byteRead = 0;
            while (byteRead < messageLength) {
                long l = socketChannel.read(byteBuffers);
                byteRead += l; //累计读取的字节数
                System.out.println("byteRead=" + byteRead);
                Arrays.asList(byteBuffers)
                        .stream()
                        .map(buffer -> "postion = " + buffer.position() + ", limit = " + buffer.limit())
                        .forEach(System.out::println);
            }

            //将所有的buffer进行flip
            Arrays.asList(byteBuffers).stream().forEach(buffer -> buffer.flip());

            long byteWrite = 0 ;
            while (byteWrite < messageLength){
                long l = socketChannel.write(byteBuffers);
                byteWrite += l;
            }

            //将所有的buffer进行clear
            Arrays.asList(byteBuffers).forEach(byteBuffer -> byteBuffer.clear());



        }

    }


}
