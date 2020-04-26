package dynamicLog;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.slf4j.LoggerFactory;

/***
 * 日志对象工厂
 */
public class Log4jFactory {
    private static final String ENCODING = "utf-8";
    private static final String CONVERSION_PATTERN = "%d [%-5p] %t %m%n";

    public static org.slf4j.Logger createSlf4jLogger(String logType){
        logType = StringUtils.defaultString(logType,"common");
        createLogger(logType);
        return LoggerFactory.getLogger(logType);
    }

    private static Logger createLogger(String logType) {
        DailyRollingFileAppender appender = new DailyRollingFileAppender();
        PatternLayout layout = new PatternLayout();
        layout.setConversionPattern(CONVERSION_PATTERN);
        appender.setLayout(layout);
        //设置输出路径
        String basetPah = "D:\\log\\dynamic-%s-log.log";
        basetPah = String.format(basetPah,logType);
        appender.setFile(basetPah);
        appender.setEncoding(ENCODING);
        appender.setAppend(true);
        appender.activateOptions();
        Logger logger = Logger.getLogger(logType);
        logger.setLevel(Level.INFO);
        logger.setAdditivity(false);
        logger.addAppender(appender);
        return logger;
    }
}
