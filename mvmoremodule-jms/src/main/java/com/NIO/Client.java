package com.NIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.concurrent.TimeUnit;

/***
 * 客户端
 */
public class Client {

    public static void main(String[] args){
        Client.client();
    }


    public static void client() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        SocketChannel socketChannel = null;
        try{
            socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false); //设置成非阻塞
            socketChannel.connect(new InetSocketAddress("localhost" , 8000));
            if(socketChannel.finishConnect()){
                int i = 0 ;
                while (true){
                    TimeUnit.SECONDS.sleep(5);
                    String info = "I'm "+i+++"-th information from client";
                    byteBuffer.clear();
                    byteBuffer.put(info.getBytes());
                    byteBuffer.flip();
                    while (byteBuffer.hasRemaining()){
                        System.out.println(byteBuffer);
                        socketChannel.write(byteBuffer);
                    }
                }
            }
        }catch (Exception e){
            e.getStackTrace();
        }finally {
            if(socketChannel != null){
                try {
                    socketChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
