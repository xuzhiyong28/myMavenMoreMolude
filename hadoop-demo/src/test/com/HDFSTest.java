package com;/**
 * Created by Administrator on 2019-02-17.
 */

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.IOUtils;
import org.junit.Test;

import java.io.*;
import java.net.URL;

/**
 * @author xuzhiyong
 * @createDate 2019-02-17-10:22
 */
public class HDFSTest {

    private static final String HDFS_URL = "hdfs://192.168.199.128:9000";

    @Test
    public void test1() throws Exception {
        //设置可以使用hdfs协议
        URL.setURLStreamHandlerFactory(new FsUrlStreamHandlerFactory());
        URL url = new URL("hdfs://192.168.199.128:9000/hello.txt");
        InputStream inputStream = url.openStream();
        IOUtils.copyBytes(inputStream,System.out,4096,true);
    }

    @Test
    public void test2() throws IOException {
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS",HDFS_URL);
        FileSystem fileSystem = FileSystem.get(conf);
        //创建一个hdfs存储目录
        boolean success = fileSystem.mkdirs(new Path("/msb"));
        System.out.println(success);

        success = fileSystem.exists(new Path("/hello.txt"));
        System.out.println(success);

        //true 表示是否真正的删除
        success = fileSystem.delete(new Path("/msb"),true);
        System.out.println(success);
    }

    /***
     * 将文件传送给hadoop
     * @throws IOException
     */
    @Test
    public void test3() throws IOException {
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS",HDFS_URL);
        FileSystem fileSystem = FileSystem.get(conf);
        //true好像表示有就追加的意思,将文件放到test目录
        FSDataOutputStream out = fileSystem.create(new Path("/nginx.conf"),true);
        FileInputStream fis = new FileInputStream("D:/nginx.conf");
        IOUtils.copyBytes(fis,out,4096,true);
    }

    @Test
    public void test4() throws IOException {
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS",HDFS_URL);
        FileSystem fileSystem = FileSystem.get(conf);
        FileStatus[] fileStatuses = fileSystem.listStatus(new Path("/"));
        for(FileStatus fs : fileStatuses){
            System.out.println(fs.getPath() + "  " + fs.getPermission() + "  " + fs.getReplication());
        }
    }

    @Test
    public void putFileToHDFS() throws IOException {
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS",HDFS_URL);
        FileSystem fileSystem = FileSystem.get(conf);
        //创建输入流
        FileInputStream fis = new FileInputStream(new File("d:/1.docx"));
        //获取输出流
        FSDataOutputStream fos = fileSystem.create(new Path("/1.doc"));
        //流对拷
        IOUtils.copyBytes(fis,fos,conf);
        IOUtils.closeStream(fos);
        IOUtils.closeStream(fis);
        fileSystem.close();
    }

    @Test
    public void getFileFromHDFS() throws IOException {
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS",HDFS_URL);
        FileSystem fileSystem = FileSystem.get(conf);
        //获取输入流
        FSDataInputStream fis = fileSystem.open(new Path("/1.doc"));
        //获取输出流
        FileOutputStream fos = new FileOutputStream("d:/1.docx");
        //流对拷
        IOUtils.copyBytes(fis,fos,conf);
        IOUtils.closeStream(fos);
        IOUtils.closeStream(fis);
        fileSystem.close();
    }

}
