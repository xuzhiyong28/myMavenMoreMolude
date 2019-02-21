package com.mashibing;/**
 * Created by Administrator on 2019-02-19.
 */

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * @author xuzhiyong
 * @createDate 2019-02-19-21:56
 */
public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration configuration = new Configuration();
        configuration.set("fs.defaultFS","hdfs://192.168.199.128:9000/");
        //configuration.set("mapreduce.job.jar","wc.jar");
        //configuration.set("mapreduce.framework.name","yarn");
        //configuration.set("yarn.resourcemanager.hostname","server-1");
        //configuration.set("mapreduce.app.submission.corss-platform","true");

        Job job = Job.getInstance(configuration);
        job.setMapperClass(WordMapper.class);
        job.setReducerClass(WordReducer.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(LongWritable.class);

        FileInputFormat.setInputPaths(job,new Path("/wcinput/"));
        FileOutputFormat.setOutputPath(job,new Path("/wcoutput/"));
        job.waitForCompletion(true);

    }
}
