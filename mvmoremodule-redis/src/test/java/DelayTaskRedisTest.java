import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Tuple;

import java.util.Calendar;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class DelayTaskRedisTest {

    private static final String ADDR = "127.0.0.1";
    private static final int PORT = 6379;
    private static JedisPool jedisPool = new JedisPool(ADDR, PORT);

    public static Jedis getJedis() {
        return jedisPool.getResource();
    }

    public static void main(String args[]) throws InterruptedException {
        DelayTaskRedisTest t = new DelayTaskRedisTest();
        t.productionDelayMessage();
        t.consumerDelayMessage();
    }

    public void productionDelayMessage() {
        for (int i = 0; i < 5; i++) {
            //延迟3秒
            Calendar cal1 = Calendar.getInstance();
            cal1.add(Calendar.SECOND, 3);
            int second3later = (int) (cal1.getTimeInMillis() / 1000);
            DelayTaskRedisTest.getJedis().zadd("OrderId", second3later, "OID0000001" + i);
            System.out.println(System.currentTimeMillis() + "ms:redis生成了一个订单任务：订单ID为" + "OID0000001" + i);
        }
    }

    /***
     * 如果多个并发消费者，这里会有并发问题，需要用到分布式锁
     * @throws InterruptedException
     */
    public void consumerDelayMessage() throws InterruptedException {
        Jedis jedis = DelayTaskRedisTest.getJedis();
        while (true) {
            Set<Tuple> items = jedis.zrangeWithScores("OrderId", 0, 1); //取第一个
            if (items == null || items.isEmpty()) {
                System.out.println("当前没有等待的任务");
                TimeUnit.MILLISECONDS.sleep(500);
                continue;
            }
            int score = (int) ((Tuple) items.toArray()[0]).getScore();
            Calendar cal = Calendar.getInstance();
            int nowSecond = (int) (cal.getTimeInMillis() / 1000);
            if (nowSecond >= score) {
                String orderId = ((Tuple) items.toArray()[0]).getElement();
                jedis.zrem("OrderId", orderId); //删除对应的值
                System.out.println(System.currentTimeMillis() + "ms:redis消费了一个任务：消费的订单OrderId为" + orderId);
            }
        }
    }
}
