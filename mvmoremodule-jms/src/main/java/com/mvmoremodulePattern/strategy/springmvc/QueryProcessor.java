package com.mvmoremodulePattern.strategy.springmvc;

public interface QueryProcessor {
    boolean check(String type);
    void handel();
}
