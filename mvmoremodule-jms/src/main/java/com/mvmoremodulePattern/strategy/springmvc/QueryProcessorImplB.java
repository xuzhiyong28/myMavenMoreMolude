package com.mvmoremodulePattern.strategy.springmvc;

import org.apache.commons.lang.StringUtils;

public class QueryProcessorImplB implements QueryProcessor{
    @Override
    public boolean check(String type) {
        if(StringUtils.equals(type,"B")){
            System.out.println("QueryProcessorImplB-check");
            return true;
        }
        return false;
    }

    @Override
    public void handel() {
        System.out.println("QueryProcessorImplB-handel");
    }
}
