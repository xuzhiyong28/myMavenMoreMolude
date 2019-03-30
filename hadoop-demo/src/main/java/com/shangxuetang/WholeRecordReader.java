package com.shangxuetang;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.ByteWritable;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;

/**
 * @author xuzhiyong
 * @createDate 2019-03-30-18:23
 */
public class WholeRecordReader extends RecordReader<Text, ByteWritable> {

    FileSplit split;
    Configuration conf;

    private boolean isProgress = true;
    private BytesWritable value = new BytesWritable();
    private Text k = new Text();

    @Override
    public void initialize(InputSplit inputSplit, TaskAttemptContext taskAttemptContext) throws IOException, InterruptedException {
        //初始化
        this.split = (FileSplit) inputSplit;
        this.conf = taskAttemptContext.getConfiguration();

    }

    @Override
    public boolean nextKeyValue() throws IOException, InterruptedException {
        if (isProgress) {
            //核心业务逻辑
            byte[] contents = new byte[(int) split.getLength()];
            FileSystem fs = null;
            FSDataInputStream fis = null;
            try {
                //获取文件系统
                Path path = split.getPath();
                fs = path.getFileSystem(conf);
                //读取数据
                fis = fs.open(path);
                //读取内容
                IOUtils.readFully(fis, contents, 0, contents.length);
                //输出文件内容
                value.set(contents, 0, contents.length);
                //获取文件路径及名称
                String name = split.getPath().toString();
                //设置输出的key值
                k.set(name);

            } catch (Exception e) {

            } finally {
                IOUtils.closeStream(fis);
            }
            isProgress = false;
        }

        return true;
    }

    @Override
    public Text getCurrentKey() throws IOException, InterruptedException {
        Text k = new Text();
        return null;
    }

    @Override
    public ByteWritable getCurrentValue() throws IOException, InterruptedException {
        return null;
    }

    @Override
    public float getProgress() throws IOException, InterruptedException {
        return 0;
    }

    @Override
    public void close() throws IOException {

    }
}
