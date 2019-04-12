package com.shangxuetang;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @author xuzhiyong
 * @createDate 2019-03-30-16:56
 */
public class KVTextMapper extends Mapper<Text, Text,Text, LongWritable> {

    LongWritable v = new LongWritable(1);
    @Override
    protected void map(Text key, Text value, Context context) throws IOException, InterruptedException {
        context.write(key,v);
    }
}
