package xzy.reactor;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.test.StepVerifier;

import java.time.Duration;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;

public class Test {

    @org.junit.Test
    public void test1() {
        Flux.just(1, 2, 3, 4, 5, 6, 7).subscribe(System.out::println);
        Mono.just(101).subscribe(System.out::println);
    }

    @org.junit.Test
    public void test2() {
        Flux.just(1, 2, 3, 4, 5, 6, 7).subscribe(System.out::println, throwable -> System.out.println(throwable), () -> System.out.println("complate"));
        System.out.println("===========分割线============");
        Flux.range(1, 4).map(i -> {
            if (i <= 3) {
                return i;
            }
            throw new RuntimeException("Got to 4");
        }).subscribe(System.out::println, throwable -> System.out.println(throwable));
    }

    /***
     * StepVerifier 用来测试Reactor的工具类
     * expectNext用于测试下一个期望的数据元素
     * expectErrorMessage用于校验下一个元素是否为错误信号
     * expectComplete用于测试下一个元素是否为完成信号
     */
    @org.junit.Test
    public void test3() {
        Flux<Integer> flux = Flux.just(1, 2, 3, 4, 5, 6);
        StepVerifier.create(flux)
                .expectNext(1, 2, 3, 4, 5, 6)
                .expectComplete()
                .verify();
        Mono<Exception> mono = Mono.error(new Exception("some error"));
        StepVerifier.create(mono)
                .expectErrorMessage("some error")
                .verify();

    }

    @org.junit.Test
    public void test4() {
        Flux.range(1, 6).map(integer -> integer * integer).subscribe(System.out::println);
    }

    /*对于每一个字符串s，将其拆分为包含一个字符的字符串流；
    对每个元素延迟100ms；
    对每个元素进行打印（注doOnNext方法是“偷窥式”的方法，不会消费数据流）；*/
    @org.junit.Test
    public void test5() throws InterruptedException {
        StepVerifier.create(
                Flux.just("flux", "mono")
                        .flatMap(s -> Flux.fromArray(s.split("\\s*"))
                                .delayElements(Duration.ofMillis(100)))
                        .doOnNext(System.out::print)
        )
        .expectNextCount(8)
        .verifyComplete();
    }

    /***
     * 过滤
     */
    @org.junit.Test
    public void test6() {
        Flux.range(1, 6)
                .filter(i -> i % 2 == 1)
                .subscribe(System.out::print);
    }

    /***
     * zip用来根据拉链的方式合并
     * Flux.interval声明一个每200ms发出一个元素的long数据流
     * @throws InterruptedException
     */
    @org.junit.Test
    public void test7() throws InterruptedException {
        String desc = "Zip two sources together, that is to say wait for all the sources to emit one element and combine these elements once into a Tuple2.";
        Flux<String> stringFlux = Flux.fromArray(desc.split("\\s*"));
        CountDownLatch countDownLatch = new CountDownLatch(1);
        Flux.zip(stringFlux, Flux.interval(Duration.ofMillis(200)))
                .subscribe(t -> System.out.println(t.getT1()), null, countDownLatch::countDown);
        countDownLatch.await(10, TimeUnit.SECONDS);

    }


    @org.junit.Test
    public void test8(){
        SampleSubscriber<Integer> sampleSubscriber = new SampleSubscriber<Integer>();
        Flux<Integer> flux = Flux.range(1,4);
        flux.subscribe(
                System.out::println,
                error -> System.out.println("Error : " + error),
                () -> System.out.println("Done"),
                subscription -> sampleSubscriber.request(10)
        );
    }

    class SampleSubscriber<T> extends BaseSubscriber<T>{
        @Override
        protected void hookOnSubscribe(Subscription subscription) {
            System.out.println("Subscribed");
            request(1);
        }

        @Override
        protected void hookOnNext(T value) {
            System.out.println("value = " + value);
            request(1);
        }
    }

    /***
     * 使用fromCallable声明一个基于Callable的Mono；
     * 使用subscribeOn将任务调度到Schedulers内置的弹性线程池执行，弹性线程池会为Callable的执行任务分配一个单独的线程。
     * @throws InterruptedException
     */
    @org.junit.Test
    public void test9() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        Mono.fromCallable(() -> {
            TimeUnit.SECONDS.sleep(2);
            return "Hello, Reactor!";
        }).subscribeOn(Schedulers.elastic()).subscribe(System.out::println, null, countDownLatch::countDown);
        countDownLatch.await(10, TimeUnit.SECONDS);
    }



}
