package xzy;
import org.junit.Test;
import org.openjdk.jol.info.ClassLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class OtherTest {
    private static ReentrantLock reentrantLock = new ReentrantLock();

    public static void main(String[] args){
        Object[] obj = new Object[10];
        System.out.println(ClassLayout.parseInstance(obj).toPrintable());
    }


    @Test
    public void test0(){
        int k =  1 + (1 << 16);
        k = k + (1 << 16);
        System.out.println(k);
        System.out.println(k >>> 16);
    }
    
    @Test
    public void test1() throws InterruptedException {
        HashMap<String,Integer> hashMap = new HashMap<>(8);
        hashMap.put("admin.com",1);
        hashMap.put("a32dmin.com",1);
        hashMap.put("ad343min.com",1);
        hashMap.put("adm446in.com",1);
        hashMap.put("admi67n.com",1);
        hashMap.put("admi878n.com",1);
        hashMap.put("admi453n.com",1);
        hashMap.put("admin.454com",1);
        hashMap.put("admin.45com",1);

    }





}
