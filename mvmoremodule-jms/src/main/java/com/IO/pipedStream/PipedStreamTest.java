package com.IO.pipedStream;/**
 * Created by Administrator on 2018-05-10.
 */

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.concurrent.TimeUnit;

/**
 * @author xuzhiyong
 * @createDate 2018-05-10-17:18
 */
public class PipedStreamTest {
    public static void main(String agrs[]){
        Sender sender = new Sender();
        Receiver receiver = new Receiver();
        PipedOutputStream out = sender.getOut();
        PipedInputStream in = receiver.getIn();
        try {
            in.connect(out); //输入流关联输出流
            receiver.start(); //接收者启线程
            TimeUnit.SECONDS.sleep(2);
            sender.start(); //发送者启线程
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
