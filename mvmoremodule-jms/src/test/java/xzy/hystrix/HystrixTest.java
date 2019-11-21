package xzy.hystrix;

import com.netflix.hystrix.HystrixCommand;
import org.junit.Test;

/**
 * @author xuzhiyong
 * @createDate 2019-10-31-21:45
 */
public class HystrixTest {

    @Test
    public void test(){
        GetStockServiceCommand command = new GetStockServiceCommand();
        String result = command.execute();
        System.out.println(result);
    }

    @Test
    public void test2() throws InterruptedException {
        for(int i = 0 ; i < 25 ; i++){
            Thread.sleep(500);
            HystrixCommand<String> command = new GetOrderCircuitBreakerCommand("testCircuitBreaker");
            String result = command.execute();
            //本例子中从第11次，熔断器开始打开
            System.out.println("call times:"+(i+1)+"   result:"+result +" isCircuitBreakerOpen: "+command.isCircuitBreakerOpen());
            //本例子中5s以后，熔断器尝试关闭，放开新的请求进来
        }
    }

}
