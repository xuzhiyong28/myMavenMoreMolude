package com.IO;/**
 * Created by Administrator on 2018-05-11.
 */

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

/**
 * @author xuzhiyong
 * @createDate 2018-05-11-8:58
 * 测试通过缓冲流读取文件的速度
 */
public class BufferedStreamTest {
    private static String FILE_NAME = "D:\\jxtg.mkv";

    public static void main(String agrs[]) throws Exception {
        //readByInputStream();
        readByBufferedStream();
    }

    public static void readByInputStream() throws Exception {
        FileInputStream fileInputStream = new FileInputStream(FILE_NAME);
        byte[] bytes = new byte[1024 * 4];
        long start = System.currentTimeMillis();
        while (fileInputStream.read(bytes, 0, bytes.length) != -1) {

        }
        long end = System.currentTimeMillis();
        System.out.println("读取的耗时:" + (end - start));
        fileInputStream.close();
    }

    public static void readByBufferedStream() throws Exception{
        FileInputStream fileInputStream = new FileInputStream(FILE_NAME);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
        byte[] bytes = new byte[1024 * 4];
        long start = System.currentTimeMillis();
        while (bufferedInputStream.read(bytes, 0, bytes.length) != -1) {
        }
        long end = System.currentTimeMillis();
        System.out.println("读取的耗时:" + (end - start));
        bufferedInputStream.close();
    }

}
