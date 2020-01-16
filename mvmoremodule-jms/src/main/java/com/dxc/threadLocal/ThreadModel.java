package com.dxc.threadLocal;

public class ThreadModel {
    private String name;


    public ThreadModel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
