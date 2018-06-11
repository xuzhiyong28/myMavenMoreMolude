package com.JVM;/**
 * Created by Administrator on 2018-04-29.
 */

import java.util.ArrayList;
import java.util.List;

/**
 * @author xuzhiyong
 * @createDate 2018-04-29-8:11
 * -XX:PermSize=1M -XX:MaxPermSize=2M
 */
public class TestPermSpace {
    public static void main(String agrs[]){
        List<Object> list = new ArrayList<Object>();
        int i = 0;
        while(true){
            i++ ;
            //intern是在方法区的常量池创建字符串
            list.add(String.valueOf(i++).intern());
        }
    }
}
