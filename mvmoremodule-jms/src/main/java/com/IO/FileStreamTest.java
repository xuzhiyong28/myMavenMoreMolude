package com.IO;/**
 * Created by Administrator on 2018-05-10.
 */

import com.IO.pojo.Box;

import java.io.*;

/**
 * @author xuzhiyong
 * @createDate 2018-05-10-18:51
 */
public class FileStreamTest {
    private static final String FileName = "D:\\file.txt";

    public static void main(String agrs[]) throws IOException {
        //testWrite();
        testRead();
    }

    private static void testWrite() throws IOException {
        File file = new File(FileName);
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        PrintStream printStream = new PrintStream(fileOutputStream); //用printStream包装处理流，可以方便的完成输出的功能
        printStream.println(100);
        printStream.println("许志勇");
        printStream.close();

        FileOutputStream fileOutputStream2 = new FileOutputStream(file, true); //后面加一个true表示不新建文件，而是在文件后面追加内容
        PrintStream printStream2 = new PrintStream(fileOutputStream2);
        Box box = new Box("正方形", 100, 100);
        printStream2.println(box);
        printStream2.close();
    }

    private static void testRead() throws IOException {
        File file = new File(FileName);
        FileInputStream fileInputStream1 = new FileInputStream(file);
        char c1 = (char) fileInputStream1.read(); //读取一个字符
        System.out.println("c1=" + c1);
        fileInputStream1.close();

    }

}
