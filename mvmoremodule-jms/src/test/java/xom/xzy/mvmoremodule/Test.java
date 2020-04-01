package xom.xzy.mvmoremodule;/**
 * Created by Administrator on 2018-03-30.
 */

import com.dxc.util.CallbackTask;
import com.dxc.util.CallbackTaskScheduler;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

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
        List<Future<Void>> futures = executorService.invokeAll(reqTasks, 10, TimeUnit.SECONDS);
        for (Future<Void> f : futures) {
            try {
                f.get();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        System.out.println(testModel.number);
    }

    class TestModel {
        public int number;
    }

    class TestCallable implements Callable<Void> {
        private TestModel testModel;

        public TestCallable(TestModel testModel) {
            this.testModel = testModel;
        }

        @Override
        public Void call() {
            printNumber();
            return null;
        }

        public void printNumber() {
            //如果这里用this来加锁的话，其实每个只是加锁了每一个TestCallable对象，会有问题
            synchronized (object) {
                for (int j = 0; j < 10000; j++) {
                    testModel.number += 1;
                }
            }
        }
    }

    @org.junit.Test
    public void test6() throws IOException {
        String[] protocols = {"http", "https", "httpipv6", "httpsipv6"};
        String[] isps = {"dx", "lt"};
        String[] channels = {"wscdn.qthls.qq.com","qqlive.hdl.xdns.com","qtlol.hdl.lxdns.com"};
        String[] fileTypes = {"other","hls"};
        List<String> result = Lists.newArrayList();
        for(String channel : channels){
            long time = 1575561600L;
            for(String protocol : protocols){
                for(String isp : isps){
                    for(String fileType : fileTypes){
                        for(int i = 0 ; i < 1440 ; i++){
                            String sql = "sub {time} {channel} {fileType} {protocol} 0 8216 {isp} {guid} {random}";
                            sql = sql.replace("{time}",String.valueOf(time))
                                    .replace("{channel}",channel)
                                    .replace("{fileType}",fileType)
                                    .replace("{protocol}",protocol)
                                    .replace("{isp}",isp)
                                    .replace("{guid}" , "guid_" + RandomUtils.nextInt(1,200))
                                    .replace("{random}",String.valueOf(RandomUtils.nextInt(1,100000)));
                            time += 60;
                            result.add(sql);
                        }
                    }
                }
            }
        }
        FileUtils.writeLines(new File("D:\\qq_guid.txt"),result);
    }

    @org.junit.Test
    public void test7(){
        String str1 = "tct1.douyucdn.cn;hdlsa.douyucdn.cn;tct3.douyucdn.cn;scdnplws3a.douyucdn.cn;shotz3.douyutv.com;push1a.douyu.com;hdl3abak.douyutv.com;p2pchunk-ws.douyucdn.8686c.com;ali-cdn-send1.douyu.com;vplay3a.douyutv.com;send1.qietv.douyu.com;forward1tc.qiecdn.com;japanplws3a.douyucdn.cn;huayunb1a.douyutv.com;techb1a.douyutv.com;hdla.douyucdn.8686c.com;hdlahwhy.douyucdn.cn;gwrt1a.douyutv.com;hdl3hwhy.douyucdn.cn;shiyun-rt3.douyutv.com;ptplws1.douyucdn.cn;ali-rt3.douyutv.com;send-peixun-coop.qiecdn.com;ali-cdn-send3a.douyu.com;hls1a.douyutv.com;shiyun-rt1.douyutv.com;hdl1-monitor.douyucdn.cn;japanplws1.douyucdn.cn;ali-rt1.douyutv.com;hdl3a-monitor-internal.douyucdn.cn;dyscdnws1.douyucdn.cn;hdl3-monitor-internal.douyucdn.cn;hls1.qiecdn.com;hls3.douyucdn.cn;hlsa.douyucdn.cn;hdlap2p.douyucdn.8686c.com;shotz1a.douyutv.com;vdplws1a.douyucdn.cn;drt3a.douyutv.com;tct3.douyutv.com;hdl8.douyucdn.8686c.com;hdl1.qiecdn.com;dyscdnws3.douyucdn.cn;tct1.douyutv.com;hdl3.douyucdn.8686c.com;.m0dycdn.cdn.douyutv.com;transza.douyutv.com;hdl3cmcc.douyucdn.cn;send4c.douyutv.com;hdlabak.douyutv.com;hls-test-pull.qiecdn.com;hlsa.douyutv.com;tct1a.douyutv.com;p2ptest2-hdl.douyutv.com;japanplws3.douyucdn.cn;vplay1a.douyucdn.cn;hdl3abak.douyucdn.cn;ptplws3.douyucdn.cn;scdnplws1a.douyucdn.cn;drt3.douyutv.com;hdl2.qiecdn.com;hdl3-monitor.douyucdn.cn;drt1.douyutv.com;transz3.douyutv.com;hdl1ap2p.douyucdn.8686c.com;vdplws1.douyucdn.cn;hdl4c.douyucdn.cn;hdl3acmcc.douyucdn.cn;peerstar-hdl3.douyucdn.cn;hdl1ahwhy.douyucdn.cn;hls1a.douyucdn.cn;forward1tc.douyucdn.cn;ws-p2ptest3-hdl.douyutv.com;hdl1abak.douyutv.com;p2ptest3-hdl.douyutv.com;shiyun-rt1a.douyutv.com;hls1a-s-akm.douyucdn.cn;hls3.douyutv.com;xyrt3a.douyutv.com;gwrt1.douyutv.com;hdl1acmcc.douyucdn.cn;hlsa-akm.douyucdn.cn;japanplws1a.douyucdn.cn;tct3a.douyucdn.cn;hdl3ahwhy.douyucdn.cn;rt2.qiecdn.com;hdl-test-pull.qiecdn.com;streaming.qiecdn.com;hls-broadcast.qiecdn.com;send3-lm.douyu.8686c.com;hdl1a.douyucdn.cn;send3.douyutv.com;tlrt1a.douyutv.com;wasuhdl1.douyutv.com;hdl3realtime.douyutv.com;send1.douyutv.com;rt3.douyutv.com;hkatvb1a.douyutv.com;rt1.douyutv.com;send3.douyu.com;send3a.douyutv.com;ptplws3a.douyucdn.cn;vdplws3.douyucdn.cn;peerstar-hdl3a.douyucdn.cn;hdl1a.douyutv.com;push3a.douyu.com;hdl1.qietv.douyucdn.cn;ali-rt1a.douyutv.com;hdl3a.douyucdn.8686c.com;rt1.douyucdn.cn;techb1.douyutv.com;hdl1abak.douyucdn.cn;cibn-rt3a.douyutv.com;p2ptest4-hdl.douyutv.com;peerstar-hdla.douyucdn.cn;p2ptable-ws.douyucdn.cn;rt3.douyucdn.cn;techb3.douyutv.com;ws-p2ptest4-hdl.douyutv.com;p2ptest1-hdl.douyutv.com;rt1.qiecdn.com;xyrt1a.douyutv.com;ahcm-rt1.douyucdn.cn;transz1a.douyutv.com;cloudacc-rt1a.douyutv.com;hls3a.douyucdn.cn;ahcm-rt3.douyucdn.cn;hdlabak.douyucdn.cn;send1.douyu.com;tct1a.douyucdn.cn;hls3a-s-akm.douyucdn.cn;hdls3a.douyucdn.cn;hlsa-s-akm.douyucdn.cn;p2pchunk-ws.douyucdn.cn;hdl3p2p.douyucdn.8686c.com;scdnplws1.douyucdn.cn;mi-rt3.douyutv.com;rt80d4bee3.qiecdn.com;hdl3.douyucdn.cn;gwrt3.douyutv.com;hls3a.douyutv.com;huayunb3.douyutv.com;scdnplws3.douyucdn.cn;mi-rt1.douyutv.com;hdl1a.douyucdn.8686c.com;huayunb1.douyutv.com;push1.douyu.com;cloudacc-rt1.douyutv.com;cloudacc-rt3.douyutv.com;hdl3ap2p.douyucdn.8686c.com;shotz3a.douyutv.com;vdplws3a.douyucdn.cn;hdl5.douyucdn.cn;hdl6.douyucdn.cn;wasuhdl.douyutv.com;drt1a.douyutv.com;rt1.qietv.douyucdn.cn;ahcm-rt3a.douyucdn.cn;hdl3.douyutv.com;hdl7.douyucdn.8686c.com;dyscdnws1a.douyucdn.cn;ahcm-rt1a.douyucdn.cn;dyscdnws3a.douyucdn.cn;hls3a-akm.douyucdn.cn;cibn-rt1.douyutv.com;hdl6.douyutv.com;send1.qiecdn.com;hdl-broadcast.qiecdn.com;hdl5.douyutv.com;shiyun-rt13a.douyutv.com;cibn-rt3.douyutv.com;hls3-akm.douyucdn.cn;tct3a.douyutv.com;xyrt1.douyutv.com;xyrt3.douyutv.com;hdl-peixun-coop.qiecdn.com;hls2.qiecdn.com;p2plive-ws.douyucdn.cn;vplay3a.douyucdn.cn;hkatvb3.douyutv.com;ali-rt3a.douyutv.com;hkatvb1.douyutv.com;cibn-rt1a.douyutv.com;huayunb3a.douyutv.com;hdl1-monitor-internal.douyucdn.cn;send2.qiecdn.com;mi-rt13a.douyutv.com;push3.douyu.com;tlrt1.douyutv.com;transz3a.douyutv.com;hdls3.douyucdn.cn;gwrt3a.douyutv.com;hls4c.douyucdn.cn;hdl3a-monitor.douyucdn.cn;ali-cdn-send3.douyu.com;hls-peixun-coop.qiecdn.com;tlrt3.douyutv.com;ali-cdn-send1a.douyu.com;cloudacc-rt3a.douyutv.com;hdls1a.douyucdn.cn;vplay1a.douyutv.com;techb3a.douyutv.com;hdl3bak.douyutv.com;shotza.douyutv.com;hdla.douyutv.com;send3a.douyu.com;peerstar-hdl1a.douyucdn.cn;hdl3bak.douyucdn.cn;hdl3a.douyucdn.cn;ptplws1a.douyucdn.cn;rtmp-3dc2e0peixun-coop.qiecdn.com;peerstar-cdn.douyucdn.cn;hls1a-akm.douyucdn.cn;hls1.qietv.douyucdn.cn;hdlacmcc.douyucdn.cn;rtmp-test-push.qiecdn.com;tlrt3a.douyutv.com;send1a.douyu.com;hls3-s-akm.douyucdn.cn;hdla.douyucdn.cn;hkatvb3a.douyutv.com;hdl1a-monitor.douyucdn.cn;mi-rt1a.douyutv.com;hdl3a.douyutv.com;hdl1a-monitor-internal.douyucdn.cn;send1a.douyutv.com;acfun.tv";
        String str2 = "tct1.douyucdn.cn;hdlsa.douyucdn.cn;tct3.douyucdn.cn;scdnplws3a.douyucdn.cn;shotz3.douyutv.com;push1a.douyu.com;hdl3abak.douyutv.com;p2pchunk-ws.douyucdn.8686c.com;ali-cdn-send1.douyu.com;vplay3a.douyutv.com;send1.qietv.douyu.com;forward1tc.qiecdn.com;japanplws3a.douyucdn.cn;huayunb1a.douyutv.com;techb1a.douyutv.com;hdla.douyucdn.8686c.com;hdlahwhy.douyucdn.cn;gwrt1a.douyutv.com;hdl3hwhy.douyucdn.cn;shiyun-rt3.douyutv.com;ptplws1.douyucdn.cn;ali-rt3.douyutv.com;send-peixun-coop.qiecdn.com;ali-cdn-send3a.douyu.com;hls1a.douyutv.com;shiyun-rt1.douyutv.com;hdl1-monitor.douyucdn.cn;japanplws1.douyucdn.cn;ali-rt1.douyutv.com;hdl3a-monitor-internal.douyucdn.cn;dyscdnws1.douyucdn.cn;hdl3-monitor-internal.douyucdn.cn;hls1.qiecdn.com;hls3.douyucdn.cn;hlsa.douyucdn.cn;hdlap2p.douyucdn.8686c.com;shotz1a.douyutv.com;vdplws1a.douyucdn.cn;drt3a.douyutv.com;tct3.douyutv.com;hdl8.douyucdn.8686c.com;hdl1.qiecdn.com;dyscdnws3.douyucdn.cn;tct1.douyutv.com;hdl3.douyucdn.8686c.com;.m0dycdn.cdn.douyutv.com;transza.douyutv.com;hdl3cmcc.douyucdn.cn;send4c.douyutv.com;hdlabak.douyutv.com;hls-test-pull.qiecdn.com;hlsa.douyutv.com;tct1a.douyutv.com;p2ptest2-hdl.douyutv.com;japanplws3.douyucdn.cn;vplay1a.douyucdn.cn;hdl3abak.douyucdn.cn;ptplws3.douyucdn.cn;scdnplws1a.douyucdn.cn;drt3.douyutv.com;hdl2.qiecdn.com;hdl3-monitor.douyucdn.cn;drt1.douyutv.com;transz3.douyutv.com;hdl1ap2p.douyucdn.8686c.com;vdplws1.douyucdn.cn;hdl4c.douyucdn.cn;hdl3acmcc.douyucdn.cn;peerstar-hdl3.douyucdn.cn;hdl1ahwhy.douyucdn.cn;hls1a.douyucdn.cn;forward1tc.douyucdn.cn;ws-p2ptest3-hdl.douyutv.com;hdl1abak.douyutv.com;p2ptest3-hdl.douyutv.com;shiyun-rt1a.douyutv.com;hls1a-s-akm.douyucdn.cn;hls3.douyutv.com;xyrt3a.douyutv.com;gwrt1.douyutv.com;hdl1acmcc.douyucdn.cn;hlsa-akm.douyucdn.cn;japanplws1a.douyucdn.cn;tct3a.douyucdn.cn;hdl3ahwhy.douyucdn.cn;rt2.qiecdn.com;hdl-test-pull.qiecdn.com;streaming.qiecdn.com;hls-broadcast.qiecdn.com;send3-lm.douyu.8686c.com;hdl1a.douyucdn.cn;send3.douyutv.com;pushdouyu3.douyu.com;tlrt1a.douyutv.com;wasuhdl1.douyutv.com;pushdouyu1a.douyu.com;hdl3realtime.douyutv.com;send1.douyutv.com;rt3.douyutv.com;hkatvb1a.douyutv.com;rt1.douyutv.com;send3.douyu.com;send3a.douyutv.com;ptplws3a.douyucdn.cn;vdplws3.douyucdn.cn;peerstar-hdl3a.douyucdn.cn;hdl1a.douyutv.com;push3a.douyu.com;hdl1.qietv.douyucdn.cn;ali-rt1a.douyutv.com;hdl3a.douyucdn.8686c.com;rt1.douyucdn.cn;techb1.douyutv.com;hdl1abak.douyucdn.cn;cibn-rt3a.douyutv.com;p2ptest4-hdl.douyutv.com;peerstar-hdla.douyucdn.cn;p2ptable-ws.douyucdn.cn;rt3.douyucdn.cn;techb3.douyutv.com;ws-p2ptest4-hdl.douyutv.com;p2ptest1-hdl.douyutv.com;pushdouyu1.douyu.com;rt1.qiecdn.com;xyrt1a.douyutv.com;ahcm-rt1.douyucdn.cn;transz1a.douyutv.com;cloudacc-rt1a.douyutv.com;hls3a.douyucdn.cn;ahcm-rt3.douyucdn.cn;hdlabak.douyucdn.cn;send1.douyu.com;tct1a.douyucdn.cn;hls3a-s-akm.douyucdn.cn;hdls3a.douyucdn.cn;hlsa-s-akm.douyucdn.cn;p2pchunk-ws.douyucdn.cn;pushdouyu3a.douyu.com;hdl3p2p.douyucdn.8686c.com;scdnplws1.douyucdn.cn;mi-rt3.douyutv.com;rt80d4bee3.qiecdn.com;hdl3.douyucdn.cn;gwrt3.douyutv.com;hls3a.douyutv.com;huayunb3.douyutv.com;scdnplws3.douyucdn.cn;mi-rt1.douyutv.com;hdl1a.douyucdn.8686c.com;huayunb1.douyutv.com;push1.douyu.com;cloudacc-rt1.douyutv.com;cloudacc-rt3.douyutv.com;hdl3ap2p.douyucdn.8686c.com;shotz3a.douyutv.com;vdplws3a.douyucdn.cn;hdl5.douyucdn.cn;hdl6.douyucdn.cn;wasuhdl.douyutv.com;drt1a.douyutv.com;rt1.qietv.douyucdn.cn;ahcm-rt3a.douyucdn.cn;hdl3.douyutv.com;hdl7.douyucdn.8686c.com;dyscdnws1a.douyucdn.cn;ahcm-rt1a.douyucdn.cn;dyscdnws3a.douyucdn.cn;hls3a-akm.douyucdn.cn;cibn-rt1.douyutv.com;hdl6.douyutv.com;send1.qiecdn.com;hdl-broadcast.qiecdn.com;hdl5.douyutv.com;shiyun-rt13a.douyutv.com;cibn-rt3.douyutv.com;hls3-akm.douyucdn.cn;tct3a.douyutv.com;xyrt1.douyutv.com;xyrt3.douyutv.com;hdl-peixun-coop.qiecdn.com;hls2.qiecdn.com;p2plive-ws.douyucdn.cn;vplay3a.douyucdn.cn;hkatvb3.douyutv.com;ali-rt3a.douyutv.com;hkatvb1.douyutv.com;cibn-rt1a.douyutv.com;huayunb3a.douyutv.com;hdl1-monitor-internal.douyucdn.cn;send2.qiecdn.com;mi-rt13a.douyutv.com;push3.douyu.com;tlrt1.douyutv.com;transz3a.douyutv.com;hdls3.douyucdn.cn;gwrt3a.douyutv.com;hls4c.douyucdn.cn;hdl3a-monitor.douyucdn.cn;ali-cdn-send3.douyu.com;hls-peixun-coop.qiecdn.com;tlrt3.douyutv.com;ali-cdn-send1a.douyu.com;cloudacc-rt3a.douyutv.com;hdls1a.douyucdn.cn;vplay1a.douyutv.com;techb3a.douyutv.com;hdl3bak.douyutv.com;shotza.douyutv.com;hdla.douyutv.com;send3a.douyu.com;peerstar-hdl1a.douyucdn.cn;hdl3bak.douyucdn.cn;hdl3a.douyucdn.cn;ptplws1a.douyucdn.cn;rtmp-3dc2e0peixun-coop.qiecdn.com;peerstar-cdn.douyucdn.cn;hls1a-akm.douyucdn.cn;hls1.qietv.douyucdn.cn;hdlacmcc.douyucdn.cn;rtmp-test-push.qiecdn.com;tlrt3a.douyutv.com;send1a.douyu.com;hls3-s-akm.douyucdn.cn;hdla.douyucdn.cn;hkatvb3a.douyutv.com;hdl1a-monitor.douyucdn.cn;mi-rt1a.douyutv.com;hdl3a.douyutv.com;hdl1a-monitor-internal.douyucdn.cn;send1a.douyutv.com;acfun.tv";
        List<String> str2List = Lists.newArrayList(StringUtils.split(str2,";"));
        List<String> str1List = Lists.newArrayList(StringUtils.split(str1,";"));
        Set<String> stringSet1 = new HashSet<>(str1List);
        Set<String> stringSet2 = new HashSet<>(str2List);
        stringSet2.removeAll(stringSet1);
        System.out.println(stringSet2);
    }

    @org.junit.Test
    public void test8() {
        MyTestModel[] testModels = new MyTestModel[]{
            new MyTestModel("1","1"),
            new MyTestModel("2","2"),
            new MyTestModel("3","3")
        };
        int len = testModels.length;
        MyTestModel[] newArray = Arrays.copyOf(testModels, len + 1);
        testModels[0].age = "999";
        newArray[3] = new MyTestModel("4","4");
        for(MyTestModel model : testModels){
            System.out.println(model.toString());
        }
        for(MyTestModel model : newArray){
            System.out.println(model.toString());
        }
    }

    @org.junit.Test
    public void test9(){
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        lock.lock();
        System.out.println("!!!");
        lock.unlock();
        lock.unlock();
    }

    @org.junit.Test
    public void test10() throws InterruptedException {
        CallbackTaskScheduler.add(new CallbackTask<String>() {
            @Override
            public String execute() throws Exception {
                System.out.println("=======execute=========");
                return null;
            }

            @Override
            public void OnBack(String s) {
                System.out.println("=======OnBack=========");
            }

            @Override
            public void onException(Throwable t) {

            }
        });
    }
}
