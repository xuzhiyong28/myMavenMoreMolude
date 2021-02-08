package xzy.designPattern.decorate;

import org.junit.Test;

public class DecorateTest {

    @Test
    public void test0(){
        LruDecorateCache cache = new LruDecorateCache(new CommonCache());
        cache.putObject("","");
    }
}
