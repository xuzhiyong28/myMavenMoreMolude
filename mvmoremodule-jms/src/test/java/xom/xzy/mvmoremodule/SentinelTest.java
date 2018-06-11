package xom.xzy.mvmoremodule;/**
 * Created by Administrator on 2018-03-31.
 */

import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

import java.util.HashSet;
import java.util.Set;

/**
 * @author xuzhiyong
 * @createDate 2018-03-31-15:38
 */
public class SentinelTest {
    private static final JedisSentinelPool jedisSentinelPool;
    static {
        String masterName  = "mymaster";
        Set<String> redisSet = new HashSet<String>();
        redisSet.add("192.168.0.110:26379");
        GenericObjectPoolConfig gPoolConfig = new GenericObjectPoolConfig();
        gPoolConfig.setMaxIdle(1000);
        gPoolConfig.setMaxTotal(1000);
        gPoolConfig.setMaxWaitMillis(100000);
        gPoolConfig.setJmxEnabled(true);
        jedisSentinelPool = new JedisSentinelPool(masterName,redisSet,gPoolConfig);
    }
    public SentinelTest(){

    }

    public static void getByName(String key){
        Jedis jedis = jedisSentinelPool.getResource();
        System.out.println(jedis.get(key));

    }

}
