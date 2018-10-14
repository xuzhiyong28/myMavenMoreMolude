package com.xzy.masterDemo;/**
 * Created by Administrator on 2018-10-14.
 */

import java.io.Serializable;

/**
 * Created by nevermore on 16/6/22.
 */
public class RunningData implements Serializable {

    private static final long serialVersionUID = 4260577459043203630L;

    //服务器id
    private long cid;
    //服务器名称
    private String name;


    public long getCid() {
        return cid;
    }

    public void setCid(long cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
