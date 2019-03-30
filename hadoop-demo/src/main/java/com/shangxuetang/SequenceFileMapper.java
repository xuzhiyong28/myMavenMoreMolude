package com.shangxuetang;

import org.apache.hadoop.io.ByteWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @author xuzhiyong
 * @createDate 2019-03-30-21:40
 */
public class SequenceFileMapper extends Mapper<Text, ByteWritable,Text,ByteWritable> {
    @Override
    protected void map(Text key, ByteWritable value, Context context) throws IOException, InterruptedException {
        context.write(key,value);
    }
}
