package xzy.java8;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import edu.emory.mathcs.backport.java.util.Collections;
import org.apache.commons.lang3.RandomUtils;
import org.openjdk.jol.info.GraphLayout;
import xzy.java8.lambda.Apple;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;

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
                while (true) {
                    if (map.containsKey("xuzy")) {
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



    public List<String> changeList(List<String> src) {
        List<String> dict = Lists.newArrayList();
        for (String line : src) {
            String[] array = line.split(",");
            dict.add(array[0] + "_" + array[1] + "_" + array[2]);
        }
        return dict;
    }

    public List<String> changeDomainList(List<String> src) {
        List<String> dict = Lists.newArrayList();
        for (String line : src) {
            String[] array = line.split(",");
            dict.add(array[1]);
        }
        return dict;
    }


    public void bidui(List<String>... srcArray) {
        List<String> result = Lists.newArrayList();
        int i = 0;
        for (List<String> src : srcArray) {
            int finalI = i;
            src.stream().forEach(s -> {
                if (!result.contains(s)) {
                    result.add(s);
                } else {
                    System.out.println(finalI + "_存在重复数据 " + s);
                }
            });
            i++;
        }

    }

    @org.junit.Test
    public void iteratorTest() {
        ArrayList list = new ArrayList();
        list.add("a");
        list.add("b");
        list.add("c");
        Iterator it = list.iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            System.out.println(str);
            if (str.equals("c")) {
                it.remove();
            }
        }
    }


    /***
     * JOL  查看对象的内存大小
     */
    @org.junit.Test
    public void calculationObjectSize() {
        Map<String, String> map = Maps.newHashMap();
        for (int i = 0; i < 10000; i++) {
            map.put(String.valueOf(i),String.valueOf(i));
        }
        long l = GraphLayout.parseInstance(map).totalSize();
        System.out.println("10000个HahsMap对象大小:" + l + " byte");

        //空对象的大小
        Map<String,String> map2 = Maps.newHashMap();
        System.out.println("空HashMap大小:" + GraphLayout.parseInstance(map2).totalSize());
    }

    @org.junit.Test
    public void priorityBlockingQueueTest(){
        List<String> list = Lists.newArrayList("a","b","c");
        Map<String,Integer> map = list.stream().collect(Collectors.toMap(new Function<String, String>() {
            @Override
            public String apply(String s) {
                return s;
            }
        }, new Function<String, Integer>() {
            @Override
            public Integer apply(String s) {
                return 1;
            }
        }));
        System.out.println(map);
        System.out.println(Long.valueOf("0.00"));
    }


}
