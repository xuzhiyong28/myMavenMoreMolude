package com.socket.test2;/**
 * Created by Administrator on 2018-06-09.
 */

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author xuzhiyong
 * @createDate 2018-06-09-18:41
 */
public class ServerSocketMany {
    public static void main(String agrs[]){
        try {
            ServerSocket serverSocket = new ServerSocket(5555);
            AtomicInteger ai = new AtomicInteger();
            Socket socket;
            while(true){
                socket = serverSocket.accept();
                Thread thread = new ServerThread(socket);
                thread.start();
                ai.incrementAndGet();
                System.out.println("当前链接的客户端的数量为：" + ai.get() + "个....");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class ServerThread extends Thread{
        Socket socket;
        public ServerThread(Socket socket){
            this.socket = socket;
        }

        @Override
        public void run() {
            InputStream in = null;
            InputStreamReader isr = null;
            BufferedReader br = null;
            OutputStream out = null;
            PrintWriter pw =  null;
             try {
                in = socket.getInputStream();
                isr = new InputStreamReader(in);
                br = new BufferedReader(isr);
                String data = null;
                while((data = br.readLine()) != null){
                    System.out.println("我是服务器，客户端说：" + data);
                }
                socket.shutdownInput(); //关闭Soket输入流这样就不会关闭连接

                 out = socket.getOutputStream();
                 pw = new PrintWriter(out);
                 pw.println("用户名和密码正确");
                 pw.flush();
                 socket.shutdownOutput();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                 try {
                     if(null != pw) pw.close();
                     if(null != out) out.close();
                     if(null != br) br.close();
                     if(null != isr) isr.close();
                     if(null != in) in.close();
                     if(null != socket) socket.close();
                 } catch (IOException e) {
                     e.printStackTrace();
                 }
             }

        }
    }

}
