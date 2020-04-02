package dynamicLog;

import com.google.common.collect.Maps;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.slf4j.Logger;

import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class LogUtil {
    public static final String DEFAULT = "common";
    public static final int LEVEL_DEBUG = 0;
    public static final int LEVEL_INFO = 1;
    public static final int LEVEL_WARN = 2;
    public static final int LEVEL_ERROR = 3;
    private static volatile Map<String, Logger> DATATYPE_LOGGER_MAP = Maps.newHashMap();
    /***
     * 用来执行日志打印的线程
     */
    private static Executor LOGGER_THREAD_POOL = new ThreadPoolExecutor(10, 20, 60,
            TimeUnit.SECONDS, new LinkedBlockingQueue<>(), new ThreadFactoryBuilder().setNameFormat("log-pool-%d").build());


    public static void debug(String content, Object[] args){
        addQueue(LEVEL_DEBUG,content,args);
    }


    private static void addQueue(int level, String content, Object[] args) {
        Logger logger = getLogger();
        LOGGER_THREAD_POOL.execute(new LoggerThread(logger,content,args,level));
    }


    /***
     * 根据不通的logType创建日志对象并获取日志对象
     * @return
     */
    private static Logger getLogger() {
        String logType = UuidHelper.getLogType() != null ? UuidHelper.getLogType() : DEFAULT;
        Logger logger = DATATYPE_LOGGER_MAP.get(logType);
        if(logger == null){
            synchronized (LogUtil.class){
                if(null == logger){
                    //双检查锁
                    if((logger = DATATYPE_LOGGER_MAP.get(logType)) == null){
                        logger = Log4jFactory.createSlf4jLogger(logType);
                        DATATYPE_LOGGER_MAP.put(logType,logger);
                    }
                }
            }
        }
        return logger;
    }


}
