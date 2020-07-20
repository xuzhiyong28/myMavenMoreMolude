package com.xzy.rwDynamic;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/***
 * 动态数据库
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    protected Object determineCurrentLookupKey() {
        return DBContextHolder.get();
    }
}
