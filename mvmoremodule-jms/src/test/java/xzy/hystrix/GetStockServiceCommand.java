package xzy.hystrix;

import com.netflix.hystrix.*;

/**
 * @author xuzhiyong
 * @createDate 2019-10-31-21:21
 */
public class GetStockServiceCommand extends HystrixCommand<String> {

    public GetStockServiceCommand(){
        super(setter());
    }

    @Override
    protected String run() throws Exception {
        return VirtualHttp.getHttpPost();
    }

    private static Setter setter(){
        //服务分组
        HystrixCommandGroupKey groupKey = HystrixCommandGroupKey.Factory.asKey("stock");
        //服务标识
        HystrixCommandKey commandKey = HystrixCommandKey.Factory.asKey("getStock");
        //线程池名称
        HystrixThreadPoolKey threadPoolKey = HystrixThreadPoolKey.Factory.asKey("stock-pool");
        //线程池配置
        HystrixThreadPoolProperties.Setter threadPoolSetter = HystrixThreadPoolProperties.Setter()
                .withCoreSize(10)
                .withKeepAliveTimeMinutes(5)
                .withMaxQueueSize(Integer.MAX_VALUE)
                .withQueueSizeRejectionThreshold(10000);
        //命令属性配置
        HystrixCommandProperties.Setter commonSetter = HystrixCommandProperties.Setter()
                .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.THREAD);
        return HystrixCommand.Setter.withGroupKey(groupKey)
                .andCommandKey(commandKey)
                .andThreadPoolKey(threadPoolKey)
                .andCommandPropertiesDefaults(commonSetter)
                .andThreadPoolPropertiesDefaults(threadPoolSetter);
    }

    @Override
    protected String getFallback() {
        return "400";
    }
}
