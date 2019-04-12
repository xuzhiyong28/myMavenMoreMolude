package com.shangxuetang;


import org.apache.hadoop.io.ByteWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;

import java.io.IOException;

/**
 * @author xuzhiyong
 * @createDate 2019-03-30-18:19
 */
public class WholeFileInputformat extends FileInputFormat<Text, ByteWritable> {


    @Override
    public RecordReader<Text, ByteWritable> createRecordReader(InputSplit inputSplit, TaskAttemptContext taskAttemptContext) throws IOException, InterruptedException {
        WholeRecordReader wholeRecordReader = new WholeRecordReader();
        wholeRecordReader.initialize(inputSplit,taskAttemptContext);
        return wholeRecordReader;
    }
}
