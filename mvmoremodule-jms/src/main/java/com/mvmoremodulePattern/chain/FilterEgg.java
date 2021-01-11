package com.mvmoremodulePattern.chain;

public class FilterEgg implements Filter{
    @Override
    public void doFilter(String data) {
        System.out.println("过滤鸡蛋");
    }
}
