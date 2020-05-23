package xzy.java8;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import edu.emory.mathcs.backport.java.util.Collections;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomUtils;
import xzy.java8.lambda.Apple;

import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author xuzhiyong
 * @createDate 2019-07-30-21:21
 */
public class Test {

    @org.junit.Test
    public void test1() {
        List<String> names = Lists.newArrayList("peter", "anna", "mike", "xenia");
        Collections.sort(names, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        names.sort((a, b) -> b.compareTo(a));


    }

    @org.junit.Test
    public void test2() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("!!");
            }
        });
        new Thread(() -> System.out.println("!!!!"));
    }

    @org.junit.Test
    public void test3() {
        Converter<String, Integer> converter = from -> Integer.valueOf(from);
        Integer number = converter.convert("123");
        System.out.println("number :" + number);
    }

    @org.junit.Test
    public void test4() {
        Function<String, Integer> toInteger = Integer::valueOf;
    }

    @org.junit.Test
    public void test5() {
        HashMap<String, String> map = new HashMap<>();
        map.put("name", "xuzy");
    }

    @org.junit.Test
    public void test6() {
        int length = 16;
        for (int i = 0; i < 1000; i++) {
            System.out.print(RandomUtils.nextInt(1000, 200000) & length - 1);
            System.out.print(" ");
        }
    }

    @org.junit.Test
    public void test7() {
        ConcurrentLinkedQueue<Integer> queue = new ConcurrentLinkedQueue();
        queue.offer(1);
        Integer value = queue.poll();
        Integer value2 = queue.poll();
    }

    @org.junit.Test
    public void test8() throws InterruptedException {
        Map<String, Apple> map = Maps.newConcurrentMap();
        map.put("xuzy", new Apple("appli", 1, 1));
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    if(map.containsKey("xuzy")){
                        System.out.println(map.get("xuzy").getColor());
                    }
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                map.remove("xuzy");
            }
        }).start();
        TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
    }


    @org.junit.Test
    public void test9() throws IOException {
        List<String> data0 = changeList(FileUtils.readLines(new File("E:\\bcs文档\\开发备忘文档\\死锁排查\\DATA0.TXT")));
        List<String> data1 = changeList(FileUtils.readLines(new File("E:\\bcs文档\\开发备忘文档\\死锁排查\\DATA1.TXT")));
        List<String> data2 = changeList(FileUtils.readLines(new File("E:\\bcs文档\\开发备忘文档\\死锁排查\\DATA2.TXT")));
        List<String> data3 = changeList(FileUtils.readLines(new File("E:\\bcs文档\\开发备忘文档\\死锁排查\\DATA3.TXT")));
        List<String> data4 = changeList(FileUtils.readLines(new File("E:\\bcs文档\\开发备忘文档\\死锁排查\\DATA4.TXT")));
        List<String> data5 = changeList(FileUtils.readLines(new File("E:\\bcs文档\\开发备忘文档\\死锁排查\\DATA5.TXT")));
        List<String> data6 = changeList(FileUtils.readLines(new File("E:\\bcs文档\\开发备忘文档\\死锁排查\\DATA6.TXT")));
        List<String> data7 = changeList(FileUtils.readLines(new File("E:\\bcs文档\\开发备忘文档\\死锁排查\\DATA7.TXT")));
        List<String> data8 = changeList(FileUtils.readLines(new File("E:\\bcs文档\\开发备忘文档\\死锁排查\\DATA8.TXT")));
        List<String> data9 = changeList(FileUtils.readLines(new File("E:\\bcs文档\\开发备忘文档\\死锁排查\\DATA9.TXT")));
        List<String> data10 = changeList(FileUtils.readLines(new File("E:\\bcs文档\\开发备忘文档\\死锁排查\\DATA10.TXT")));
        List<String> data11 = changeList(FileUtils.readLines(new File("E:\\bcs文档\\开发备忘文档\\死锁排查\\DATA11.TXT")));
        List<String> data12 = changeList(FileUtils.readLines(new File("E:\\bcs文档\\开发备忘文档\\死锁排查\\DATA12.TXT")));
        //bidui(data0,data1,data2,data3,data4,data5,data6,data7,data8,data9,data10,data11,data12);
        bidui(data8,data10);
    }

    @org.junit.Test
    public void test10() throws IOException {
        List<String> data1 = changeDomainList(FileUtils.readLines(new File("E:\\bcs文档\\开发备忘文档\\死锁排查\\DATA7.TXT")));
        List<String> data2 = changeDomainList(FileUtils.readLines(new File("E:\\bcs文档\\开发备忘文档\\死锁排查\\DATA6.TXT")));
        for(String domain : data1){
            if(data2.contains(domain)){
                System.out.println("重复数据 =" + domain);
            }
        }

    }


    public List<String> changeList(List<String> src){
        List<String> dict  = Lists.newArrayList();
        for(String line : src){
            String[] array = line.split(",");
            dict.add(array[0] + "_" + array[1] + "_" + array[2]);
        }
        return dict;
    }

    public List<String> changeDomainList(List<String> src){
        List<String> dict  = Lists.newArrayList();
        for(String line : src){
            String[] array = line.split(",");
            dict.add(array[1]);
        }
        return dict;
    }


    public void bidui(List<String> ...srcArray){
        List<String> result = Lists.newArrayList();
        int i = 0;
        for(List<String> src : srcArray){
            int finalI = i;
            src.stream().forEach(s -> {
                if(!result.contains(s)){
                    result.add(s);
                }else{
                    System.out.println( finalI + "_存在重复数据 " + s);
                }
            });
            i++;
        }

    }







}
