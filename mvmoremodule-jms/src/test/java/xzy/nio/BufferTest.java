package xzy.nio;

import org.junit.Test;

import java.nio.ByteBuffer;

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
}
