package xzy;

import org.junit.Test;
import org.openjdk.jol.info.ClassLayout;

public class OtherTest {

    public static void main(String[] args){
        Object[] obj = new Object[10];
        System.out.println(ClassLayout.parseInstance(obj).toPrintable());

    }
}
