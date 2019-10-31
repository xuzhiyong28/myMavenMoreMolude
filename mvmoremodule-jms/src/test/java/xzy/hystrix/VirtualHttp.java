package xzy.hystrix;

import java.util.concurrent.TimeUnit;

/**
 * @author xuzhiyong
 * @createDate 2019-10-31-21:42
 */
public class VirtualHttp {
    public static String getHttpPost(){
        System.out.println("=====模拟发送请求========");
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "200";
    }
}
