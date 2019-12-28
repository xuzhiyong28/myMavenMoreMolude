package com.nio;

import jdk.management.resource.internal.inst.FileChannelImplRMHooks;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;

/**
 * @author xuzhiyong
 * @createDate 2019-12-17-22:23
 */
public class Test {
    @org.junit.Test
    public void testBuffer(){
        //Buffer的测试
        //创建一个buffer，大小为5 ，就是可以存5个int
        IntBuffer intBuffer = IntBuffer.allocate(5);
        //往buffer存放数据
        for(int i = 0 ; i < intBuffer.capacity() ; i++){
            intBuffer.put(i * 2);
        }
        //取数据

        intBuffer.flip(); //读写切换，读写之间需要切换   **重要**

        while (intBuffer.hasRemaining()){
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
        FileChannel fileChannel =fileInputStream.getChannel();
        //创建缓冲区
        ByteBuffer byteBuffer =  ByteBuffer.allocate((int)file.length());
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

        while (true){
            //每次循环都清空下byteBuffer
            byteBuffer.clear();
            int read = fileChannel1.read(byteBuffer);
            if(read == -1) {
                break;
            }
            //将buffer中的数据写入到fileChannel2
            byteBuffer.flip();
            fileChannel2.write(byteBuffer);
        }
        fileInputStream.close();
        fileOutputStream.close();
    }
}
