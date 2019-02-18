package com;/**
 * Created by Administrator on 2019-02-18.
 */

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.junit.Test;

import java.io.IOException;

/**
 * @author xuzhiyong
 * @createDate 2019-02-18-20:59
 */
public class WordCountTest {


    @Test
    public void test() throws IOException {
        Job job = Job.getInstance(new Configuration());
        job.setJarByClass(WordCountTest.class);
        job.setMapperClass(WcMapper.class);
        job.setReducerClass(WcReducer.class);
        FileInputFormat.setInputPaths(null,new Path("hdfs://192.168.199.128:9000/wcinput/input.txt"));
    }

    class WcMapper extends Mapper<LongWritable,Text,Text,LongWritable>{
        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            //接收数据
            String line = value.toString();
            //切分数据
            String[] words = line.split("");
            //循环
            for(String w : words){
                context.write(new Text(w),new LongWritable(1));
            }
        }
    }

    class WcReducer extends Reducer<Text,LongWritable,Text,LongWritable>{
        @Override
        protected void reduce(Text key, Iterable<LongWritable> v2s, Context context) throws IOException, InterruptedException {
            //定义一个计算器
            long counter = 0;
            //循环v2s
            for(LongWritable v : v2s){
                counter += v.get();
            }
            //输出
            context.write(key, new LongWritable(counter));
        }
    }

}


