package com.mvmoremoduleJms.supper;/**
 * Created by Administrator on 2018-04-09.
 */

import com.mvmoremoduleJms.webSocket.WebSocket;
import redis.clients.jedis.JedisPubSub;

import java.io.IOException;

/**
 * @author xuzhiyong
 * @createDate 2018-04-09-10:57
 * 重写
 */
public class RedisSubscriber extends JedisPubSub {
    @Override
    public void onMessage(String channel, String message) {
        System.out.println(String.format("receive redis published message, channel %s, message %s", channel, message));
        for(WebSocket webSocket : WebSocket.webSockets){
            try {
                webSocket.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public void onSubscribe(String channel, int subscribedChannels) {
        System.out.println(String.format("subscribe redis channel success, channel %s, subscribedChannels %d",
                channel, subscribedChannels));
    }
    @Override
    public void onUnsubscribe(String channel, int subscribedChannels) {
        System.out.println(String.format("unsubscribe redis channel, channel %s, subscribedChannels %d",
                channel, subscribedChannels));

    }
}
