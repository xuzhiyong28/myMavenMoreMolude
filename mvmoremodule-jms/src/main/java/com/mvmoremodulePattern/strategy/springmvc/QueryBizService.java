package com.mvmoremodulePattern.strategy.springmvc;

import java.util.List;
import java.util.function.Consumer;

public class QueryBizService {
    private List<QueryProcessor> queryProcessorList;

    public void handler(String type){
        //springmvc 就是通过参数判断check 然后找到对应的hanlder去执行
        queryProcessorList.forEach(f -> {
            if(f.check(type)){
                f.handel();
            }
        });
    }

    public void setQueryProcessorList(List<QueryProcessor> queryProcessorList) {
        this.queryProcessorList = queryProcessorList;
    }
}
