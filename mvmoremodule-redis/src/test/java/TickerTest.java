/**
 * Created by Administrator on 2018-04-28.
 */

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.concurrent.locks.Lock;

/**
 * @author xuzhiyong
 * @createDate 2018-04-28-17:48
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class TickerTest {

    private int count = 100;

    @Resource(name = "redisLock")
    Lock lock;


    @org.junit.Test
    public void tickerTest() throws InterruptedException {
        TicketRunnable tr = new TicketRunnable();
        Thread t1 = new Thread(tr, "窗口1");
        Thread t2 = new Thread(tr, "窗口2");
        Thread t3 = new Thread(tr, "窗口3");
        Thread t4 = new Thread(tr, "窗口4");
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        Thread.currentThread().join();
    }

    public class TicketRunnable implements Runnable {

        @Override
        public void run() {
            while (count > 0) {
                if (count > 0) {
                    lock.lock();
                    try {
                        System.out.println(Thread.currentThread().getName() + "卖出第" + (count--) + "张票");
                    } catch (Exception e) {

                    } finally {
                        lock.unlock();
                    }
                    try {
                        Thread.sleep(200);
                    } catch (Exception e) {

                    }
                }
            }
        }
    }

}
