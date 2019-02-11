package com.mvmoremodulePattern.adapter.objectadapter;

public class Main {
    public static void main(String[] args){
        Target target = new Adapter(new Adaptee());
        target.simpleOperation1();
        target.simpleOperation2();
    }
}
