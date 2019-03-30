package com.shangxuetang;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @author xuzhiyong
 * @createDate 2019-03-30-18:00
 */
public class NLineMapper extends Mapper<LongWritable, Text, Text , LongWritable> {

    LongWritable v = new LongWritable(1);
    Text k = new Text();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //获取一行
        String line = value.toString();
        //切割
        String[] splited = line.split(" ");
        //循环写出
        for(int i = 0 ; i < splited.length ; i++){
            k.set(splited[i]);
            context.write(k,v);
        }
    }

    /***
     * banzhang ni hao
     * xihuan hadoop banzhang
     * banzhang ni hao
     * xihuan hadoop banzhang
     * banzhang ni hao
     * xihuan hadoop banzhang
     * banzhang ni hao
     * xihuan hadoop banzhang
     * banzhang ni hao
     * xihuan hadoop banzhang banzhang ni hao
     * xihuan hadoop banzhang
     */
}
