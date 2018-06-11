package com.IO;/**
 * Created by Administrator on 2018-05-12.
 */

import java.io.*;

/**
 * @author xuzhiyong
 * @createDate 2018-05-12-11:36
 */
public class DataInputStreamTest {
    private static final int LEN = 5;
    private static final String FILE_NAME = "d:\\file.txt";

    public static void main(String agrs[]) throws IOException {
        dataOutputStreamDemo();
        dataInputStreamDemo();
    }

    public static void dataOutputStreamDemo() throws IOException {
        File file = new File(FILE_NAME);
        if (file.exists()) file.delete();
        DataOutputStream dos = new DataOutputStream(new FileOutputStream(new File(FILE_NAME)));
        String[] names = {"衬衣", "手套", "围巾"};
        float prices[] = {98.3f, 30.3f, 50.5f};        // 商品价格
        int nums[] = {3, 2, 1};
        for (int i = 0; i < names.length; i++) {
            dos.writeChars(names[i]);    // 写入字符串，注意，这边少数writeChars()，不是writechar()。
            dos.writeChar('\t');    // 写入分隔符，这边是读取writechar()。
            dos.writeFloat(prices[i]); // 写入价格
            dos.writeChar('\t');    // 写入分隔符
            dos.writeInt(nums[i]); // 写入数量
            dos.writeChar('\n');    // 换行
        }
        dos.close();
    }

    public static void dataInputStreamDemo() throws IOException {
        DataInputStream dis = new DataInputStream(new FileInputStream(new File(FILE_NAME)));
        String name = null;
        float price = 0.0f;
        int num = 0;
        char temp[] = null;
        int len = 0;
        char c = 0;
        while (true) {
            temp = new char[200];
            len = 0;
            while ((c = dis.readChar()) != '\t') {
                temp[len] = c;
                len ++;
            }
            name = new String(temp,0,len) ;
            price = dis.readFloat() ;    // 读取价格
            dis.readChar() ;    // 读取\t
            num = dis.readInt() ;    // 读取int
            dis.readChar() ;    // 读取\n
            System.out.printf("名称：%s；价格：%5.2f；数量：%d\n",name,price,num) ;
        }
    }

}
