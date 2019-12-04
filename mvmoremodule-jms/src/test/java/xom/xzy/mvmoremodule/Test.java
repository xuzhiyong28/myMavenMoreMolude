package xom.xzy.mvmoremodule;/**
 * Created by Administrator on 2018-03-30.
 */

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.util.concurrent.Futures;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;

import java.util.*;
import java.util.concurrent.*;

/**
 * @author xuzhiyong
 * @createDate 2018-03-30-19:16
 */
public class Test {
    private static Object object = new Object();



    @org.junit.Test
    public void test1() {
        ClassPathResource classPathResource = new ClassPathResource("applicationContext.xml");
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(factory);
        xmlBeanDefinitionReader.loadBeanDefinitions(classPathResource);
    }

    @org.junit.Test
    public void test2() {
        System.out.println("!!!");
        ArrayList<String> arrayList = new ArrayList<>(10);
        arrayList.add("1");
    }

    @org.junit.Test
    public void test3() {
        String s1 = "first";
        String s2 = new String("first");
        String s3 = "fi" + "rst";
        String s4 = new String("fi") + "rst";
        String s5 = new String("1") + new String("1");
        String s6 = "11";
        System.out.println(s1 == s2); // false
        System.out.println(s1 == s3); // true
        System.out.println(s2 == s4); // false
        System.out.println(s5.intern() == s6); //true
    }

    @org.junit.Test
    public void test4() {
        Map<String, Double> map = Maps.newHashMap();
        Map<String, Double> map2 = Maps.newHashMap();
        for (int i = 0; i < 60000; i++) {
            map.put("a_" + UUID.randomUUID(), RandomUtils.nextDouble(0, 100000));
        }
        long start = System.currentTimeMillis();
        BeanUtils.copyProperties(map2, map);
        System.out.println(System.currentTimeMillis() - start);
    }

    @org.junit.Test
    public void test5() throws InterruptedException {
        List<Callable<Void>> reqTasks = Lists.newArrayList();
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        TestModel testModel = new TestModel();
        for (int i = 0; i < 3; i++) {
            reqTasks.add(new TestCallable(testModel));
        }
        List<Future<Void>> futures =  executorService.invokeAll(reqTasks,10,TimeUnit.SECONDS);
        for(Future<Void> f : futures){
            try {
                f.get();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        System.out.println(testModel.number);
    }

    class TestModel{
        public int number;
    }

    class TestCallable implements Callable<Void>{
        private TestModel testModel;
        public TestCallable(TestModel testModel){
            this.testModel = testModel;
        }

        @Override
        public Void call() {
            printNumber();
            return null;
        }
        public void printNumber(){
            //如果这里用this来加锁的话，其实每个只是加锁了每一个TestCallable对象，会有问题
            synchronized (object){
                for(int j = 0 ; j < 10000 ; j++){
                    testModel.number += 1;
                }
            }
        }
    }
}
