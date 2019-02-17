package com;/**
 * Created by Administrator on 2019-02-17.
 */

import org.apache.hadoop.fs.FsUrlStreamHandlerFactory;
import org.junit.Test;

import java.io.InputStream;
import java.net.URL;
import org.apache.hadoop.io.IOUtils;
/**
 * @author xuzhiyong
 * @createDate 2019-02-17-10:22
 */
public class HDFSTest {
    @Test
    public void test1() throws Exception {
        //设置可以使用hdfs协议
        URL.setURLStreamHandlerFactory(new FsUrlStreamHandlerFactory());
        URL url = new URL("hdfs://192.168.199.128:9000/hello.txt");
        InputStream inputStream = url.openStream();
        IOUtils.copyBytes(inputStream,System.out,4096,true);
    }

}
