package com.xzy.rwDynamic;

import org.apache.commons.lang3.StringUtils;

public class DBContextHolder {
    private static final ThreadLocal<String> dbHolder = new ThreadLocal<String>();

    public static String get(){
        String db = dbHolder.get();
        return StringUtils.isNoneBlank(db) ? db : DbType.MASTER.getType();
    }

    public static void set(String type){
        if(StringUtils.isBlank(type)){
            dbHolder.set(DbType.MASTER.getType());
        }else{
            dbHolder.set(type);
        }
    }

    public static void remove(){
        dbHolder.remove();
    }


}
