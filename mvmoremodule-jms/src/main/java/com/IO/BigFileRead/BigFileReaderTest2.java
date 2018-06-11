package com.IO.BigFileRead;/**
 * Created by Administrator on 2018-05-17.
 */

import org.apache.commons.lang3.StringUtils;
import org.apache.http.impl.client.SystemDefaultHttpClient;

import java.io.*;

/**
 * @author xuzhiyong
 * @createDate 2018-05-17-14:26
 */
public class BigFileReaderTest2 {
    public static void main(String agrs[]) throws IOException {
        fastReadFile();
    }


    public static void fastReadFile() throws IOException {
        BufferedInputStream bis = null;
        BufferedReader br = null;
        try{
            bis = new BufferedInputStream(new FileInputStream(new File("D:\\service.log"))); //新建一个缓冲流
            br = new BufferedReader(new InputStreamReader(bis,"UTF-8"), 10 * 1024 * 1024); //将缓冲字节流转换成换成字符流,这样支持逐行读取
            long startTime = System.currentTimeMillis();
            while(br.ready()){
                String line = br.readLine();
                if(line != null && StringUtils.contains(line,"java.")){
                    System.out.println(line);
                }
            }
            long endTime = System.currentTimeMillis();
            System.out.println("用时:" + (endTime - startTime));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(bis != null)
                bis.close();
            if(br != null)
                br.close();
        }
    }
}
