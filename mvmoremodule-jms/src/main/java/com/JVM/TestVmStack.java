package com.JVM;/**
 * Created by Administrator on 2018-04-29.
 */

/**
 * @author xuzhiyong
 * @createDate 2018-04-29-8:36
 * -Xss256K
 */
public class TestVmStack {
    private int length = 1;
    private void method(){
        length++;
        method();
    }
    public static void main(String agrs[]){
        TestVmStack testVmStack = new TestVmStack();
       try{
           testVmStack.method();
       }catch (Exception e){
           e.printStackTrace();
       }
    }
}
