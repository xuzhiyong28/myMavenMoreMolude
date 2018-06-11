package com.JVM;/**
 * Created by Administrator on 2018-04-29.
 */

import java.util.ArrayList;
import java.util.List;

/**
 * @author xuzhiyong
 * @createDate 2018-04-29-7:40
 * -Xms5M -Xmx5M  -XX:+PrintGCDetails -verbose:gc
 * -XX:UseSerialGC
 */
public class TestHeap {
    public static void main(String agrs[]){
        String[] arrList = new String[100000000];
        /*List<Object> heapList = new ArrayList<Object>();
        int i = 0 ;
        while(true){
            i++;
            if(i % 10000 == 0) System.out.println(i);
            heapList.add(new Object());
        }*/
    }
}
