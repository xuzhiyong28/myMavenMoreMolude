package xzy.nio;

import com.google.common.base.Splitter;
import org.junit.Test;

import java.nio.ByteBuffer;
import java.util.List;

public class BufferTest {
    @Test
    public void test1() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        System.out.println("---------allocate---------");
        System.out.println(byteBuffer.capacity());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.position());

        byteBuffer.put("abcd".getBytes());
        System.out.println("---------put---------");
        System.out.println(byteBuffer.capacity());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.position());

        byteBuffer.flip();
        System.out.println("---------flip---------");
        System.out.println(byteBuffer.capacity());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.position());


        byte[] bytes = new byte[byteBuffer.limit()];
        byteBuffer.get(bytes);
        System.out.println("---------get---------");
        System.out.println(new String(bytes));
        System.out.println(byteBuffer.capacity());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.position());


        byteBuffer.rewind();
        System.out.println("---------rewind---------");
        System.out.println(byteBuffer.capacity());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.position());

        byteBuffer.clear();
        System.out.println("---------clear---------");
        System.out.println(byteBuffer.capacity());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.position());
        System.out.println((char) byteBuffer.get());
    }

    @Test
    public void test4() {
        Splitter splitter = Splitter.on(",");
        List<String> ALLList = splitter.splitToList("1,10,100,105,106,107,108,109,11,110,111,112,117,119,12,120,121,122,123,13,15,16,17,18,19,2,20,21,22,24,25,27,28,29,3,30,31,32,33,34,35,36,37,38,39,4,49,55,56,57,58,66,69,70,71,72,73,74,79,8,80,89,9,90,91,97,98,99,99991");
        List<String> APP = splitter.splitToList("90,91,97,98,99,100,101,10,102,103,111,104,113,114,117,74,126,97,116,129");
        for(String a : APP){
            if(!ALLList.contains(a)){
                System.out.println(a);
            }
        }
    }

}
