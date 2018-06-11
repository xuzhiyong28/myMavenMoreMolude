package com.IO;/**
 * Created by Administrator on 2018-05-09.
 */

import java.io.ByteArrayInputStream;

/**
 * @author xuzhiyong
 * @createDate 2018-05-09-15:26
 * 说明：
ByteArrayInputStream实际上是通过“字节数组”去保存数据。
(01) 通过ByteArrayInputStream(byte buf[]) 或 ByteArrayInputStream(byte buf[], int offset, int length) ，我们可以根据buf数组来创建字节流对象。
(02) read()的作用是从字节流中“读取下一个字节”。
(03) read(byte[] buffer, int offset, int length)的作用是从字节流读取字节数据，并写入到字节数组buffer中。offset是将字节写入到buffer的起始位置，length是写入的字节的长度。
(04) markSupported()是判断字节流是否支持“标记功能”。它一直返回true。
(05) mark(int readlimit)的作用是记录标记位置。记录标记位置之后，某一时刻调用reset()则将“字节流下一个被读取的位置”重置到“mark(int readlimit)所标记的位置”；也就是说，reset()之后再读取字节流时，是从mark(int readlimit)所标记的位置开始读取。
 */
public class ByteArrayInputStreamTest {
    //abcdefghijklmnopqrstuvwxyz
    private static final byte[] ArrayLetters = {
            0x61, 0x62, 0x63, 0x64, 0x65, 0x66, 0x67, 0x68, 0x69, 0x6A, 0x6B, 0x6C, 0x6D, 0x6E, 0x6F,
            0x70, 0x71, 0x72, 0x73, 0x74, 0x75, 0x76, 0x77, 0x78, 0x79, 0x7A
    };

    public static void tesByteArrayInputStream(){
        ByteArrayInputStream bis = new ByteArrayInputStream(ArrayLetters);
        for(int i = 0 ; i < 5 ; i++){
            if(bis.available() >= 0){
                int temp = bis.read();
                System.out.printf("%d : 0x%s\n", i, Integer.toHexString(temp));
            }
        }
        if(!bis.markSupported()){
            System.out.println("make not supported!");
            return;
        }
        // 标记“字节流中下一个被读取的位置”。即--标记“0x66”，因为因为前面已经读取了5个字节，所以下一个被读取的位置是第6个字节”
        // (01), ByteArrayInputStream类的mark(0)函数中的“参数0”是没有实际意义的。
        // (02), mark()与reset()是配套的，reset()会将“字节流中下一个被读取的位置”重置为“mark()中所保存的位置”
        bis.mark(0);
        // 跳过5个字节。跳过5个字节后，字节流中下一个被读取的值应该是“0x6B”。
        bis.skip(5);
        byte[] buf = new byte[5];
        bis.read(buf,0,5); //读取5个数据
        System.out.printf("str1=%s\n", new String(buf));

        // 重置“字节流”：即，将“字节流中下一个被读取的位置”重置到“mark()所标记的位置”，即0x66。
        bis.reset();
        // 从“重置后的字节流”中读取5个字节到buf中。即读取“0x66, 0x67, 0x68, 0x69, 0x6A”
        bis.read(buf, 0, 5);
        // 将buf转换为String字符串。“0x66, 0x67, 0x68, 0x69, 0x6A”对应字符是“fghij”
        System.out.printf("str2=%s\n", new String(buf));
    }

    public static void main(String agrs[]) {
        tesByteArrayInputStream();
    }
}
