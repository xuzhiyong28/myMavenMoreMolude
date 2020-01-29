package com.netty.protocoltcp;

/***
 * 协议包
 */
public class MessageProtocol {
    private int len; //长度 -- 关键
    private byte[] content;

    public MessageProtocol(int len, byte[] content) {
        this.len = len;
        this.content = content;
    }

    public int getLen() {
        return len;
    }

    public void setLen(int len) {
        this.len = len;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}
