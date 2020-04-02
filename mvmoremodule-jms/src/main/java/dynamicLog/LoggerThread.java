package dynamicLog;

import org.slf4j.Logger;

public class LoggerThread implements Runnable {
    private Logger logger;
    private String content;
    private Object[] args;
    private int level;

    public LoggerThread(Logger logger, String content, Object[] args, int level) {
        this.logger = logger;
        this.content = content;
        this.args = args;
        this.level = level;
    }

    @Override
    public void run() {
        switch (level){
            case LogUtil.LEVEL_DEBUG:
                logger.debug(content,args);
                break;
            case LogUtil.LEVEL_INFO:
                logger.info(content,args);
                break;
            case LogUtil.LEVEL_WARN:
                logger.warn(content,args);
                break;
            case LogUtil.LEVEL_ERROR:
                logger.error(content,args);
                break;
            default:
                break;
        }
    }
}
