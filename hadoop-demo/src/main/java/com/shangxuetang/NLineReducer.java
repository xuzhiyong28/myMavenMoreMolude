package com.shangxuetang;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @author xuzhiyong
 * @createDate 2019-03-30-18:03
 */
public class NLineReducer extends Reducer<Text, LongWritable,Text,LongWritable> {
    LongWritable v = new LongWritable();
    @Override
    protected void reduce(Text key, Iterable<LongWritable> values, Context context) throws IOException, InterruptedException {
        //汇总
        long sum = 0L;
        //汇总
        for(LongWritable value : values){
            sum += value.get();
        }
        v.set(sum);
        context.write(key,v);
    }
}
