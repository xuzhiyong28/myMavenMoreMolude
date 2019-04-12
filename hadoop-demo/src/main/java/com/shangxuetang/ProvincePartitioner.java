package com.shangxuetang;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

/**
 * @author xuzhiyong
 * @createDate 2019-03-31-12:40
 * <Text,FlowBean> 是对应mapper的输出
 * 需求:手机号136、137、138、139开头都分别放到一个独立的4个文件中，其他开头的放到一个文件中
 */
public class ProvincePartitioner extends Partitioner<Text,FlowBean> {
    @Override
    public int getPartition(Text key, FlowBean flowBean, int numProvinces) {
        //key -- 手机号
        //value -- 流量信息

        String prePhoneNum = key.toString().substring(0,3);
        int partition = 4;
        switch (prePhoneNum){
            case "136" :
                partition = 0;
                break;
            case "137" :
                partition = 1;
                break;
            case "138" :
                partition = 2;
                break;
            case "139" :
                partition = 3;
                break;
        }
        return partition;
    }
}
