package com.IO.pojo;/**
 * Created by Administrator on 2018-05-09.
 */

import java.io.Serializable;

/**
 * @author xuzhiyong
 * @createDate 2018-05-09-19:03
 */
public class Box implements Serializable {
    private int width;
    private int height;
    private String name;
    public Box(String name, int width, int height) {
        this.name = name;
        this.width = width;
        this.height = height;
    }

    @Override
    public String toString() {
        return "["+name+": ("+width+", "+height+") ]";
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
