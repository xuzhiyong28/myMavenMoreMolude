package com.mashibing;/**
 * Created by Administrator on 2019-02-19.
 */

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @author xuzhiyong
 * @createDate 2019-02-19-20:49
 */
public class WordMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String s = value.toString();
        final IntWritable ONE = new IntWritable(1);
        String[] words = s.split(" ");
        for(String word : words){
            context.write(new Text(word),ONE);
        }
    }
}
