package com.mvmoremoduleJms.activemqJms;/**
 * Created by Administrator on 2018-04-01.
 */

import com.mvmoremoduleJms.webSocket.WebSocket;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.io.IOException;

/**
 * @author xuzhiyong
 * @createDate 2018-04-01-11:10
 * 消息监听器
 */
public class QueueMessageListener implements MessageListener {
    @Override
    public void onMessage(Message message) {
        TextMessage tm = (TextMessage) message;
        try {
            System.out.println("QueueMessageListener监听到了文本消息：\t" + tm.getText());
            for(WebSocket webSocket : WebSocket.webSockets){
                try {
                    webSocket.sendMessage(tm.getText());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
