package com.NIO;

import com.IO.RandomAccessFileTest;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class Demo1 {
    public static void main(String[] agrs){
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile("D:\\1.txt","rw");
            FileChannel fileChannel = randomAccessFile.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(10);
            int byteRead = fileChannel.read(byteBuffer);
            while(byteRead != -1){
                byteBuffer.flip();
                while(byteBuffer.hasRemaining()){
                    System.out.print((char) byteBuffer.get());
                }
                byteBuffer.clear();
                byteRead = fileChannel.read(byteBuffer);
            }
            randomAccessFile.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
