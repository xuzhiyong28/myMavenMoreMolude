package com.socket.test1;/**
 * Created by Administrator on 2018-06-09.
 */

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author xuzhiyong
 * @createDate 2018-06-09-17:44
 */
public class ClientSocketDemo {

    public static void main(String[] args) {
        try {
            //1、创建客户端Socket，指定服务器地址和端口号，向服务端发送请求信息
            Socket socket = new Socket("localhost", 5555);

            //2、获取输出字节流，向服务器端发送消息
            OutputStream os = socket.getOutputStream();

            //3、将字节输出流包装为字符打印流
            PrintWriter pw = new PrintWriter(os);

            //向服务器端发送请求信息
            StringBuffer bf = new StringBuffer();
            bf.append("用户名：").append("admin");
            bf.append("密码：").append("123");
            pw.write(bf.toString());

            //刷新缓存
            pw.flush();
            //关闭Socket的输出流
            socket.shutdownOutput();

            //3、获取输入字节流,读取服务器端的响应信息
            InputStream is = socket.getInputStream();

            //将输入字节流包装成字符字符流
            InputStreamReader isr = new InputStreamReader(is);

            //为字符流添加缓冲区
            BufferedReader br = new BufferedReader(isr);

            //通过服务器端的响应信息
            String data = null;
            while(null != (data=br.readLine())){
                System.out.println("我是客户端，服务器端说："+data);
            }
            //关闭Socket输入流
            socket.shutdownInput();

            //4、关闭资源
            br.close();
            isr.close();
            is.close();
            pw.close();
            os.close();
            socket.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
