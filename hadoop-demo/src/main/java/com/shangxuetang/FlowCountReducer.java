package com.shangxuetang;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @author xuzhiyong
 * @createDate 2019-03-28-21:53
 * 最后输出的是 手机 flowbean
 */
public class FlowCountReducer extends Reducer<Text,FlowBean,Text,FlowBean> {

    FlowBean v = new FlowBean();

    @Override
    protected void reduce(Text key, Iterable<FlowBean> values, Context context) throws IOException, InterruptedException {
        //1.累加求和
        long sum_upFlow = 0L;
        long sum_dowmFlow = 0L;
        for(FlowBean flowBean : values){
            sum_dowmFlow += flowBean.getDownFlow();
            sum_upFlow += flowBean.getUpFlow();
        }
        v.set(sum_upFlow,sum_dowmFlow);
        //2.写出
        context.write(key,v);
    }
}
