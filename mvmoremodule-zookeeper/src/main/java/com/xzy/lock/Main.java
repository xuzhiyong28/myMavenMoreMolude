package com.xzy.lock;/**
 * Created by Administrator on 2018-07-26.
 */

/**
 * @author xuzhiyong
 * @createDate 2018-07-26-21:37
 */
public class Main {
    public static void main(String agrs[]){
        for(int i = 0 ; i < 10 ;i++){
            Thread t = new Thread(new RunDemo());
            t.start();
        }
    }
}
