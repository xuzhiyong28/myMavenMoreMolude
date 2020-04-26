package dynamicLog;

import com.google.common.collect.Maps;

import java.util.Map;
import java.util.UUID;

public class UuidHelper {

    private static ThreadLocal<String> threadLocal = new InheritableThreadLocal<>();

    private static final Map<String, String> UUID_DATATYPE_MAP = Maps.newConcurrentMap();

    /***
     * 设置并获取UUID， 每个线程获取到的UUID不一样且每个线程中父子线程获取一样
     * @return
     */
    public static String getUUID() {
        String uuid = threadLocal.get();
        if (uuid == null) {
            uuid = UUID.randomUUID().toString().replace("-", "");
            threadLocal.set(uuid);
        }
        return uuid;
    }

    /***
     * 设置日志的类型
     * @param dataType
     */
    public static void setLogType(String dataType) {
        UUID_DATATYPE_MAP.putIfAbsent(getUUID(), dataType);
    }

    /***
     * 获取线程对应的日志类型
     * @return
     */
    public static String getLogType(){
        return UUID_DATATYPE_MAP.get(getUUID());
    }


    /***
     * 任务结束后删除
     */
    public static void remove(){
        UUID_DATATYPE_MAP.remove(getUUID());
        threadLocal.remove();
    }

}
