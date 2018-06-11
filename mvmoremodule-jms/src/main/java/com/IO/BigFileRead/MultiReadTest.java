package com.IO.BigFileRead;/**
 * Created by Administrator on 2018-05-12.
 */

import java.io.File;
import java.io.RandomAccessFile;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author xuzhiyong
 * @createDate 2018-05-12-20:53
 */
public class MultiReadTest {
    private static final int DOWN_THREAD_NUM = 10; //起10个线程去读取指定文件
    private static final String OUT_FILE_NAME = "D:\\a.txt";
    private static final String keywords = "b";

    public static void main(String agrs[]) {
        try {
            RandomAccessFile[] outArr = new RandomAccessFile[DOWN_THREAD_NUM];
            CountDownLatch countDownLatch = new CountDownLatch(DOWN_THREAD_NUM);
            AtomicInteger atomicInteger = new AtomicInteger();
            File file = new File(OUT_FILE_NAME);
            long length = file.length(); //获取文件的大小
            long numPerThred = length / DOWN_THREAD_NUM; //每线程应该读取的字节数
            System.out.println("每个线程读取的字节数：" + numPerThred + "字节");
            long left = length % DOWN_THREAD_NUM;
            for(int i = 0 ; i < DOWN_THREAD_NUM ; i++){
                outArr[i] = new RandomAccessFile(OUT_FILE_NAME,"rw");
                if(i == DOWN_THREAD_NUM - 1){
                    //最后一个线程读取指定numPerThred+left个字节
                    new BigFileReaderTest(i * numPerThred, (i + 1) * numPerThred  + left, outArr[i],keywords,countDownLatch,atomicInteger).start();
                }else {
                    new BigFileReaderTest(i * numPerThred, (i + 1) * numPerThred , outArr[i],keywords,countDownLatch,atomicInteger).start();
                }
            }
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("最后的数量=" + atomicInteger.getAndAdd(0));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
