package com.socket.test3;/**
 * Created by Administrator on 2018-06-24.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author xuzhiyong
 * @createDate 2018-06-24-19:01
 * 采用线程池
 */
public class Server {
    final static int PORT = 8765;

    public static void main(String agrs[]) {
        ServerSocket serverSocket = null;
        try{
            serverSocket = new ServerSocket(PORT);
            System.out.println("server start");
            Socket socket = null;
            ExecutorService executor = new ThreadPoolExecutor(
                    Runtime.getRuntime().availableProcessors(),
                    50,
                    120L,
                    TimeUnit.SECONDS,
                    new ArrayBlockingQueue<Runnable>(1000)
            );
            while (true){
                socket = serverSocket.accept();
                executor.execute(new ServerHandler(socket));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    /**
     * 具体处理socket的线程
     */
    static class ServerHandler implements Runnable {
        private Socket socket;

        public ServerHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            BufferedReader in = null;
            PrintWriter out = null;
            try {
                in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
                out = new PrintWriter(this.socket.getOutputStream(), true);
                String body = null;
                while (true) {
                    body = in.readLine();
                    if (body == null) break;
                    System.out.println("Server:" + body);
                    out.println("Server response");
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (in != null) {
                    try {
                        in.close();
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
                if (out != null) {
                    try {
                        out.close();
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
                if (socket != null) {
                    try {
                        socket.close();
                    } catch (Exception e3) {
                        e3.printStackTrace();
                    }
                }
            }
        }
    }
}
