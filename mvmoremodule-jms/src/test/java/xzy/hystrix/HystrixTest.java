package xzy.hystrix;

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

}
