package com.dxc.threadLocal;/**
 * Created by Administrator on 2019-01-20.
 */

import com.google.common.collect.Maps;
import org.apache.commons.lang3.RandomUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @author xuzhiyong
 * @createDate 2019-01-20-19:51
 */
public class ThreadLocalTest {
    static ThreadLocal LOCAL = new ThreadLocal();
    static ThreadLocal LOCAL2 = new ThreadLocal();
    private static Map<String, ThreadLocal<SimpleDateFormat>> sdfMap = Maps.newHashMap();
    public final static String MDHMSS = "MMddHHmmssSSS";
    public final static String YMDHMS = "yyyyMMddHHmmss";
    public final static String YMDHMS_ = "yyyy-MM-dd HH:mm:ss";
    public final static String YMD = "yyyyMMdd";
    public final static String YMD_ = "yyyy-MM-dd";
    public final static String HMS = "HHmmss";

    /***
     * 使用ThreadLoacl保证SimpleDateFormat的安全性
     * @param pattern
     * @return
     */
    private static SimpleDateFormat getSdf(final String pattern){
        ThreadLocal<SimpleDateFormat> sdfThread = sdfMap.get(pattern);
        if(sdfThread == null){
            synchronized (ThreadLocalTest.class){
                sdfThread = sdfMap.get(pattern);
                if(sdfThread == null){
                    sdfThread = new ThreadLocal<SimpleDateFormat>(){
                        @Override
                        protected SimpleDateFormat initialValue() {
                            return new SimpleDateFormat(pattern);
                        }
                    };
                    sdfMap.put(pattern,sdfThread);
                }
            }
        }
        return sdfThread.get();
    }

    public static void main(String agrs[]){
        //SimpleDateFormat simpleDateFormat = getSdf(YMDHMS_);
        //System.out.println(simpleDateFormat);
        LOCAL.set("测试ThreadLocalMap弱引用自动回收");
        LOCAL2.set("测试ThreadLocalMap弱引用自动回收2");
        Thread thread = Thread.currentThread();
        LOCAL = null;
        System.gc();
        System.out.println("");
    }







}
