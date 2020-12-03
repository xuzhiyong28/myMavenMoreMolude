package sentinelsLoadbalan;

import org.junit.Test;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class JedisSlaveSentinelTest {

    @Test
    public void test(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(10);
        jedisPoolConfig.setMaxIdle(5);
        jedisPoolConfig.setMinIdle(5);
        // 哨兵信息
        Set<String> sentinels = new HashSet<>(Arrays.asList("192.168.135.131:26379","192.168.135.131:26380","192.168.135.131:26381"));
        JedisSlaveUtils.getAlivedSlaves("mymaster", sentinels);
        // 创建连接池
        JedisSlaveSentinelPool pool = new JedisSlaveSentinelPool("mymaster", sentinels,jedisPoolConfig);
        // 获取客户端
        Jedis jedis = pool.getResource();
        // 执行两个命令
        jedis.set("mykey", "myvalue");
        String value = jedis.get("mykey");
        System.out.println(value);
    }




}
