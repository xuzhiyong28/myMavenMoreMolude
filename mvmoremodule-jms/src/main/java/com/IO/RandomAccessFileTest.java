package com.IO;/**
 * Created by Administrator on 2018-05-12.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * @author xuzhiyong
 * @createDate 2018-05-12-16:43
 */
public class RandomAccessFileTest {
    private static final String FileNames = "D:\\file.txt";
    public static void main(String agrs[]) throws IOException {
    }

    public static void testCreateWrite(){
        try{
            File file = new File(FileNames);
            RandomAccessFile raf = new RandomAccessFile(file,"rw"); //读写
            raf.writeChars("abcdefghijklmnopqrstuvwxyz\n");
            raf.writeChars("9876543210\\n");
            raf.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void testAppendWrite() throws IOException {
        File file = new File(FileNames);
        RandomAccessFile raf = new RandomAccessFile(file,"rw");
        long fileLen = raf.length();
        raf.seek(fileLen); //定位到文件末尾
        raf.writeBoolean(true); // 占1个字节
        raf.writeByte(0x41);    // 占1个字节
        raf.writeChar('a');     // 占2个字节
        raf.writeShort(0x3c3c); // 占2个字节
        raf.writeInt(0x75);     // 占4个字节
        raf.writeLong(0x1234567890123456L); // 占8个字节
        raf.writeFloat(4.7f);  // 占4个字节
        raf.writeDouble(8.256);// 占8个字节
        raf.writeUTF("UTF严"); // UTF-8格式写入
        raf.writeChar('\n');   // 占2个字符。“换行符”
        raf.close();
    }

    public static void testRead() throws IOException {
        File file = new File(FileNames);
        RandomAccessFile raf = new RandomAccessFile(file,"r");
        char c1 = raf.readChar();
        System.out.println("c1="+c1);
     // 读取一个字符
        char c2 = raf.readChar();
        System.out.println("c2="+c2);
        raf.seek(54);
        byte[] buf = new byte[20];
        raf.read(buf,0,buf.length);
        System.out.println("buf="+(new String(buf)));
        raf.close();
    }



}

