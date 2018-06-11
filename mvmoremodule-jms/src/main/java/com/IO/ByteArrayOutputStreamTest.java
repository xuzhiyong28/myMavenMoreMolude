package com.IO;/**
 * Created by Administrator on 2018-05-09.
 */

import java.io.*;

/**
 * @author xuzhiyong
 * @createDate 2018-05-09-16:09
 * 说明：
ByteArrayOutputStream实际上是将字节数据写入到“字节数组”中去。
(01) 通过ByteArrayOutputStream()创建的“字节数组输出流”对应的字节数组大小是32。
(02) 通过ByteArrayOutputStream(int size) 创建“字节数组输出流”，它对应的字节数组大小是size。
(03) write(int oneByte)的作用将int类型的oneByte换成byte类型，然后写入到输出流中。
(04) write(byte[] buffer, int offset, int len) 是将字节数组buffer写入到输出流中，offset是从buffer中读取数据的起始偏移位置，len是读取的长度。
(05) writeTo(OutputStream out) 将该“字节数组输出流”的数据全部写入到“输出流out”中。
 */
public class ByteArrayOutputStreamTest {
    private static final int LEN = 5;
    private static final byte[] ArrayLetters = {
            0x61, 0x62, 0x63, 0x64, 0x65, 0x66, 0x67, 0x68, 0x69, 0x6A, 0x6B, 0x6C, 0x6D, 0x6E, 0x6F,
            0x70, 0x71, 0x72, 0x73, 0x74, 0x75, 0x76, 0x77, 0x78, 0x79, 0x7A
    };

    public static void tesByteArrayOutputStream(){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        // 依次写入“A”、“B”、“C”三个字母。0x41对应A，0x42对应B，0x43对应C。 此函数只写入一个值
        baos.write(0x41);
        baos.write(0x42);
        baos.write(0x43);
        baos.write(ArrayLetters,3,5); //从ArrayLetters截取第三个开始写入，一共写入5个 即对应写入“0x64, 0x65, 0x66, 0x67, 0x68”，即“defgh”
        System.out.printf("baos=%s\n", baos);

        //重新写入到字节数组中来
        byte[] buf = baos.toByteArray();
        System.out.printf("str=%s\n", new String(buf));

        //写入到其他流
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream("D:\\1.txt");
            baos.writeTo((OutputStream)fileOutputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            //close
        }

    }

    public static void main(String agrs[]){
        tesByteArrayOutputStream();
    }

}
