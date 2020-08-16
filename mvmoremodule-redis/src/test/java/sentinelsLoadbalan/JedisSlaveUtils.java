package sentinelsLoadbalan;

import com.google.common.collect.Lists;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class JedisSlaveUtils {


    private List<JedisSlaveSentinelPool> slavePool = Lists.newArrayList();



    public static List<HostAndPort> getAlivedSlaves(String masterName,Set<String> sentinels) {
        List<HostAndPort> alivedSalaves = new ArrayList<>();
        for(String sentinel : sentinels){
            String ip = sentinel.split(":")[0];
            int port = Integer.valueOf(sentinel.split(":")[1]);
            Jedis jedis = null;
            try {
                jedis = new Jedis(ip, port);
                //通过哨兵获取主节点目前对应的从节点
                List<Map<String, String>> slavesInfo = jedis.sentinelSlaves(masterName);
                if (slavesInfo == null || slavesInfo.size() == 0) {
                    continue;
                }
                //获取可用的salve节点
                for (Map<String, String> slave : slavesInfo) {
                    if(slave.get("flags").equals("slave")) {
                        String slaveHost = slave.get("ip");
                        int slavePort = Integer.valueOf(slave.get("port"));
                        HostAndPort hostAndPort = new HostAndPort(slaveHost, slavePort);
                        alivedSalaves.add(hostAndPort);
                    }
                }
                break;
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                if(jedis != null){
                    jedis.close();
                }
            }
        }
        return alivedSalaves;
    }
}
