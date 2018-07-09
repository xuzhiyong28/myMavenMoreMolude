package com.socket.test3;/**
 * Created by Administrator on 2018-06-24.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author xuzhiyong
 * @createDate 2018-06-24-18:55
 */
public class Client {
    final static String ADDRESS = "127.0.0.1";
    final static int PORT = 8765;

    public static void main(String agrs[]) {
        Socket socket = null;
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            socket = new Socket(ADDRESS, PORT);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream())); //获取socket输入流来读取数据，这里先将字节流转成字符流并用缓冲流修饰
            out = new PrintWriter(socket.getOutputStream(), true);
            out.println("Client request"); //传送数据给Server

            String response = in.readLine();
            System.out.println("Client:" + response);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(in != null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(out != null){
                try {
                    out.close();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            if(socket != null){
                try {
                    socket.close();
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
            }
        }
    }
}
