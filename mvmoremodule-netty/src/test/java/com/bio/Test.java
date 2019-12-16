package com.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {

    /***
     * bio服务器
     * @throws IOException
     */
    @org.junit.Test
    public void BioServer() throws IOException {
        ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
        ServerSocket serverSocket = new ServerSocket(6666);
        System.out.println("===服务器启动===");
        while (true){
            //监听等待客户端连接
            Socket socket = serverSocket.accept();
            newCachedThreadPool.execute(() -> {
                System.out.println("===一个客户端进来：启用一个线程：" + Thread.currentThread().getId());
                byte[] bytes = new byte[1024];
                //通过socket获取输入流
                try {
                    InputStream inputStream = socket.getInputStream();
                    while (true){
                        int read = inputStream.read(bytes); //如果没有数据这里就会阻塞
                        if(read != -1){
                            System.out.println(new String(bytes ,0, read));
                        }else{
                            break;
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    if(socket != null){
                        try {
                            socket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }
    }
}
