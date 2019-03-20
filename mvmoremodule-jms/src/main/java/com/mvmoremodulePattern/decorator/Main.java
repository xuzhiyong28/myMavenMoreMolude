package com.mvmoremodulePattern.decorator;

public class Main {
    public static void main(String[] args){
        SourcableImpl sourcable = new SourcableImpl();
        //用装饰器1来装饰
        Decorator1 decorator1 = new Decorator1(sourcable);
        //用装饰器2继续装饰
        Decorator2 decorator2 = new Decorator2(decorator1);
        decorator2.operation();
    }
}
