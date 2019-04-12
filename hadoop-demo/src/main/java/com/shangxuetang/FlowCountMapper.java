package com.shangxuetang;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @author xuzhiyong
 * @createDate 2019-03-28-21:32
 * 第一个是long表示偏移量
 * 第二个是text是一行的数据
 * 第三个是输出的key是手机号 所以是text
 * 第四个是封装的对象 所以是flowbean
 */
public class FlowCountMapper extends Mapper<LongWritable, Text,Text,FlowBean> {

    Text k = new Text();
    FlowBean v = new FlowBean();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //1.获取一行
        String line = value.toString();
        //2.切割\t
        String[] fields = line.split("\t");
        //3.封装flowbean
        k.set(fields[1]); //封装手机号
        long upFlow = Long.parseLong(fields[fields.length - 3]);
        long dowmFlow = Long.parseLong(fields[fields.length - 2]);
        v.set(upFlow,dowmFlow);
        //4.写出
        context.write(k,v);
    }
}
