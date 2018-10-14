package com.xzy.kafkalog4j;/**
 * Created by Administrator on 2018-09-18.
 */

import org.apache.log4j.Logger;
/**
 * @author xuzhiyong
 * @createDate 2018-09-18-9:29
 */
public class Log4jKafkaTest {
    private static final Logger logger = Logger.getLogger(Log4jKafkaTest.class);

    public static void main(String agrs[]){
        logger.error("=============测试错误===========");
    }
}
