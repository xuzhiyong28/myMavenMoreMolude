package com.socket.test1;/**
 * Created by Administrator on 2018-06-09.
 */

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author xuzhiyong
 * @createDate 2018-06-09-17:31
 */
public class ServerSocketDemo {
    public static void main(String[] args) {
        try {
            //1、创建一个服务端Socket，即ServerSocket对象，指定绑定的端口，并侦听该端口
            ServerSocket serverSocket = new ServerSocket(5555);

            //2、调用accept()方法开始侦听客户端请求,创建Socket，等待客户端的连接
            System.out.println("===================服务器即将启动，等待客户端的连接===============");
            Socket socket = serverSocket.accept();

            //3、获取输入字节流，读取客户端请求信息
            InputStream is = socket.getInputStream();

            //将字节流包装成字符流
            InputStreamReader isr = new InputStreamReader(is);

            //为字符输入流添加缓冲
            BufferedReader br = new BufferedReader(isr);

            //读取字符输入流中的数据信息
            String data = null;
            while (null != (data = br.readLine())) {
                System.out.println("我是服务器端，客户端说：" + data);
            }
            //调用shutdown方法关闭输入流
            socket.shutdownInput();

            //4、获取输出字节流，响应客户端的信息
            OutputStream os = socket.getOutputStream();

            //将字节流包装成为字符打印流
            PrintWriter pw = new PrintWriter(os);

            //向客户端回复响应消息
            pw.write("用户名和密码输入正确");
            //刷新缓存
            pw.flush();

            //关闭socket输出流
            socket.shutdownOutput();

            //5、关闭资源
            pw.close();
            os.close();
            br.close();
            isr.close();
            is.close();
            socket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
