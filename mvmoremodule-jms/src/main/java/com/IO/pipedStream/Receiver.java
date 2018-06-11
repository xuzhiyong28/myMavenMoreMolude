package com.IO.pipedStream;/**
 * Created by Administrator on 2018-05-10.
 */


import java.io.PipedInputStream;

/**
 * @author xuzhiyong
 * @createDate 2018-05-10-16:56
 * 接收者线程
 */
public class Receiver extends Thread{

    /***
     * 管道输入流对象
     * 它和“管道输出流(PipedOutputStream)”对象绑定，从而可以接收“管道输出流”的数据，再让用户读取
     * 管道输入流的缓冲区默认大小是1024个字节。所以，最多只能写入1024个字节
     */
    private PipedInputStream in = new PipedInputStream();

    public PipedInputStream getIn() {
        return in;
    }

    //从管道流中读取一次数据
    public void readMessageOnce(){
        //虽然buf的大小是2048个字节，但最多只会从“管道输入流”中读取1024个字节.因为，“管道输入流”的缓冲区大小默认只有1024个字节
        byte[] buf = new byte[2048];
        try{
            int len = in.read(buf); //这里会阻塞
            System.out.println("===准备接收===");
            System.out.println(new String(buf,0,len));
            in.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }



    @Override
    public void run() {
        readMessageOnce();
    }
}
