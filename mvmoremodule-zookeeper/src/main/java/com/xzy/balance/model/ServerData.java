package com.xzy.balance.model;

import java.io.Serializable;

public class ServerData implements Serializable, Comparable<ServerData>{

    /***
     * 负载信息 默认是0
     */
    private Integer balance = 0;
    private String host;
    private Integer port;


    public Integer getBalance() {
        return balance;
    }
    public void setBalance(Integer balance) {
        this.balance = balance;
    }
    public String getHost() {
        return host;
    }
    public void setHost(String host) {
        this.host = host;
    }
    public Integer getPort() {
        return port;
    }
    public void setPort(Integer port) {
        this.port = port;
    }


    @Override
    public String toString() {
        return "ServerData [balance=" + balance + ", host=" + host + ", port="
                + port + "]";
    }

    /***
     * 按照负载排序
     * @param o
     * @return
     */
    public int compareTo(ServerData o) {
        return this.getBalance().compareTo(o.getBalance());
    }
}
