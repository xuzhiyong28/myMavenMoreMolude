package com.mvmoremodulePattern.adapter.classadapter;

public class Main {
    public static void main(String[] args){
        Target target = new Adapter();
        target.simpleOperation1();
        target.simpleOperation2();
    }
}
