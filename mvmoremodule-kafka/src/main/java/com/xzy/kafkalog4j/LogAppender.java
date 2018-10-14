package com.xzy.kafkalog4j;/**
 * Created by Administrator on 2018-09-18.
 */

import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.Priority;

/**
 * @author xuzhiyong
 * @createDate 2018-09-18-9:21
 */
public class LogAppender extends DailyRollingFileAppender {
    @Override
    public boolean isAsSevereAsThreshold(Priority priority) {
        return this.getThreshold().equals(priority);
    }
}
