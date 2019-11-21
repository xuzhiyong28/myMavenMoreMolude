package xzy.hystrix;

import com.netflix.hystrix.*;

import java.util.Random;

public class GetOrderCircuitBreakerCommand extends HystrixCommand<String> {

    public GetOrderCircuitBreakerCommand(String name) {
        super(setter(name));
    }

    @Override
    protected String run() throws Exception {
        Random rand = new Random();
        if (1 == rand.nextInt(2)) {
            throw new Exception("make exception");
        }
        return "running:  ";
    }

    @Override
    protected String getFallback() {
        return "fallback:  ";
    }

    private static Setter setter(String name) {
        return Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("ThreadPoolTestGroup"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("testCommandKey"))
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey(name))
                .andCommandPropertiesDefaults(
                        HystrixCommandProperties.Setter()
                                .withCircuitBreakerEnabled(true)//默认是true，是否启用熔断器，默认是TURE
                                .withCircuitBreakerForceOpen(false)//默认是false，熔断器强制打开，始终保持打开状态。默认值FLASE
                                .withCircuitBreakerForceClosed(false)//默认是false，熔断器强制关闭，始终保持关闭状态。默认值FLASE
                                .withCircuitBreakerErrorThresholdPercentage(5)//(1)错误百分比超过5%
                                .withCircuitBreakerRequestVolumeThreshold(10)//(2)10s以内调用次数10次，同时满足(1)(2)熔断器打开
                                .withCircuitBreakerSleepWindowInMilliseconds(5000)//隔5s之后，熔断器会尝试半开(关闭)，重新放进来请求
                        //.withExecutionTimeoutInMilliseconds(1000)
                )
                .andThreadPoolPropertiesDefaults(
                        HystrixThreadPoolProperties.Setter()
                                .withMaxQueueSize(10)   //配置队列大小
                                .withCoreSize(2)    // 配置线程池里的线程数
                );
    }
}
