package xzy.reactor;

import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Duration;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class Test {

    @org.junit.Test
    public void test1(){
        Flux.just(1,2,3,4,5,6,7).subscribe(System.out::println);
        Mono.just(101).subscribe(System.out::println);
    }

    @org.junit.Test
    public void test2(){
        Flux.just(1,2,3,4,5,6,7).subscribe(System.out::println, throwable -> System.out.println(throwable), () -> System.out.println("complate"));
    }

    /***
     * StepVerifier 用来测试Reactor的工具类
     * expectNext用于测试下一个期望的数据元素
     * expectErrorMessage用于校验下一个元素是否为错误信号
     * expectComplete用于测试下一个元素是否为完成信号
     */
    @org.junit.Test
    public void test3(){
        Flux<Integer> flux = Flux.just(1, 2, 3, 4, 5, 6);
        StepVerifier.create(flux)
                .expectNext(1,2,3,4,5,6)
                .expectComplete()
                .verify();
        Mono<Exception> mono = Mono.error(new Exception("some error"));
        StepVerifier.create(mono)
                .expectErrorMessage("some error")
                .verify();

    }

    @org.junit.Test
    public void test4(){
        Flux.range(1,6).map(integer -> integer * integer).subscribe(System.out::println);
    }

    /*对于每一个字符串s，将其拆分为包含一个字符的字符串流；
    对每个元素延迟100ms；
    对每个元素进行打印（注doOnNext方法是“偷窥式”的方法，不会消费数据流）；*/
    @org.junit.Test
    public void test5() throws InterruptedException {
        StepVerifier.create(
                Flux.just("flux", "mono")
                        .flatMap(s -> Flux.fromArray(s.split("\\s*"))   // 1
                                .delayElements(Duration.ofMillis(100))) // 2
                        .doOnNext(System.out::print)) // 3
                .expectNextCount(8) // 4
                .verifyComplete();
    }

    /***
     * 过滤
     */
    @org.junit.Test
    public void test6(){
        Flux.range(1,6)
                .filter(i -> i % 2 == 1)
                .subscribe(System.out::print);
    }

    @org.junit.Test
    public void test7() throws InterruptedException {
        String desc = "Zip two sources together, that is to say wait for all the sources to emit one element and combine these elements once into a Tuple2.";
        Flux<String> stringFlux = Flux.fromArray(desc.split("\\s*"));
        CountDownLatch countDownLatch = new CountDownLatch(1);
        Flux.zip(stringFlux, Flux.interval(Duration.ofMillis(200))).subscribe(t -> System.out.println(t.getT1()),null,countDownLatch::countDown);
        countDownLatch.await(10, TimeUnit.SECONDS);

    }




}
