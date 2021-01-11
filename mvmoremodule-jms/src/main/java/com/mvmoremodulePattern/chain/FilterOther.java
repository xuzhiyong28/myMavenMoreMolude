package com.mvmoremodulePattern.chain;

public class FilterOther implements Filter{
    @Override
    public void doFilter(String data) {
        System.out.println("过滤其他");
    }
}
