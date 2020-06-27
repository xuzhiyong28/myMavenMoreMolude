package com.mvmoremodulePattern.strategy.springmvc;

import com.google.common.collect.Lists;

public class Main {
     public static void main(String[] args){
         //初始化
         QueryBizService queryBizService = new QueryBizService();
         queryBizService.setQueryProcessorList(Lists.newArrayList(new QueryProcessorImplA(),new QueryProcessorImplB()));

         //执行
         queryBizService.handler("A");
         queryBizService.handler("B");
     }

}
