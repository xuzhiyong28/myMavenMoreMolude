package com.mvmoremodulePattern.strategy.springmvc;

import org.apache.commons.lang.StringUtils;

public class QueryProcessorImplA implements QueryProcessor{
    @Override
    public boolean check(String type) {
        if(StringUtils.equals(type,"A")){
            System.out.println("QueryProcessorImplA-check");
            return true;
        }
        return false;
    }

    @Override
    public void handel() {
        System.out.println("QueryProcessorImplA-handel");
    }
}
