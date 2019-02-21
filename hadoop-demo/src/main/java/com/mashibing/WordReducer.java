package com.mashibing;/**
 * Created by Administrator on 2019-02-19.
 */

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @author xuzhiyong
 * @createDate 2019-02-19-21:49
 */
public class WordReducer extends Reducer<Text,IntWritable,Text,LongWritable> {
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        long count = 0;
        for(IntWritable v : values){
            count += v.get();
        }
        context.write(key,new LongWritable(count));
    }
}
