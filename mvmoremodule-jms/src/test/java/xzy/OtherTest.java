package xzy;

import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.locks.ReentrantLock;

public class OtherTest {
    private static ReentrantLock reentrantLock = new ReentrantLock();

    public static void main(String[] args){
        Object[] obj = new Object[10];
        System.out.println(ClassLayout.parseInstance(obj).toPrintable());
    }

}
