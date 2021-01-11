package com.mvmoremodulePattern.chain;

import java.util.ArrayList;
import java.util.List;

public class FilterChain {
    List<Filter> filterList = new ArrayList<>();

    public FilterChain(){
        filterList.add(new FilterEgg());
        filterList.add(new FilterOther());
    }

    public void processData(String data){
        for (Filter filter : filterList) {
            filter.doFilter(data);
        }
    }
}
