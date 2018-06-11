package com.IO.BigFileRead;/**
 * Created by Administrator on 2018-05-12.
 */

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author xuzhiyong
 * @createDate 2018-05-12-17:02
 */
public class BigFileReaderTest extends Thread {

    //定义字节数组的长度 -- 每次取值的长度
    private final int BUFF_LEN = 256;

    private long start; //读取起点

    private long end; //读取结束点

    private RandomAccessFile raf;

    private String keywords; //关键字

    private int curCount = 0; //关键词读取次数

    private CountDownLatch doneSignal; //发令枪

    private AtomicInteger atomicInteger; //用于多线程计数

    public BigFileReaderTest(long start, long end, RandomAccessFile raf, String keywords, CountDownLatch doneSignal,AtomicInteger atomicInteger) {
        this.start = start;
        this.end = end;
        this.raf = raf;
        this.keywords = keywords;
        this.doneSignal = doneSignal;
        this.atomicInteger = atomicInteger;
    }

    @Override
    public void run() {
        try {
            raf.seek(start); //seek用于设置文件指针位置，设置后ras会从当前指针的下一位读取到或写入到
            long contentLen = end - start; //该线程需要读取的总字节数
            long times = contentLen / BUFF_LEN ; //需要读的次数
            System.out.println(this.toString() + " 需要读的次数：" + times);
            byte[] buff = new byte[BUFF_LEN];
            int hasRead = 0;
            String result = null;
            for (int i = 0; i < times; i++) {
                hasRead = raf.read(buff);
                if (hasRead < 0)
                    break;
                result = new String(buff, "UTF-8");
                int count = this.getCountByKeywords(result,keywords); //计算读取的数据中包含的关键字的字数
                if(count > 0){
                    this.curCount += count;
                }
            }
            atomicInteger.getAndAdd(this.curCount);
            doneSignal.countDown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    public RandomAccessFile getRaf() {
        return raf;
    }

    public void setRaf(RandomAccessFile raf) {
        this.raf = raf;
    }

    public int getCountByKeywords(String statement, String key) {
        return statement.split(key).length - 1;
    }

    public int getCurCount() {
        return curCount;
    }

    public void setCurCount(int curCount) {
        this.curCount = curCount;
    }

    public CountDownLatch getDoneSignal() {
        return doneSignal;
    }

    public void setDoneSignal(CountDownLatch doneSignal) {
        this.doneSignal = doneSignal;
    }



    public static void main(String agrs[]) throws IOException {
        generateBigFile();
    }
    /***
     * 生成一个文件
     * @throws IOException
     */
    public static void generateBigFile() throws IOException {
        long start = System.currentTimeMillis();
        File bigFile = new File("D:\\a.txt");
        FileWriter fw = new FileWriter(bigFile);
        for (int i = 0; i < 100000000; i++) {
            if (i % 3 == 0) {
                fw.append("b\r\n");
            }
            fw.append("aaa" + "\r\n");
        }
        fw.close();
        long end = System.currentTimeMillis();
        System.out.println("生成一个大文件 a.txt , 耗时=" + (end - start));
    }

}
