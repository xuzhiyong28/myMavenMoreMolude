package com.xzy.mvmoremodule;/**
 * Created by Administrator on 2018-09-11.
 */

import kafka.admin.AdminUtils;
import kafka.utils.ZkUtils;
import org.I0Itec.zkclient.ZkClient;
import org.apache.kafka.common.security.JaasUtils;

import java.util.Properties;

/**
 * @author xuzhiyong
 * @createDate 2018-09-11-21:35
 */
public class KafkaUtil {

    private static final String ZK_CONNECT = "192.168.199.128:2182,192.168.199.129:2182,192.168.199.130:2182";
    private static final int SESSION_TIMEOUT = 30000; //ZK SESSION过期时间
    private static final int CONNECT_TIMEOUT = 30000; //ZK 连接超时时间


    /***
     *
     * @param topic 主题名称
     * @param partition 分区数
     * @param repilca 副本数
     * @param properties 配置项
     */
    public static void createTopic(String topic, int partition, int repilca, Properties properties) {
        ZkUtils zkUtils = null;
        try {
            zkUtils = ZkUtils.apply(ZK_CONNECT, SESSION_TIMEOUT, CONNECT_TIMEOUT, JaasUtils.isZkSecurityEnabled());
            if (!AdminUtils.topicExists(zkUtils, topic)) {
                AdminUtils.createTopic(zkUtils , topic, partition , repilca , properties ,AdminUtils.createTopic$default$6());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args){
       KafkaUtil.createTopic("myTopicTwo", 3, 1, new Properties());
    }

}
