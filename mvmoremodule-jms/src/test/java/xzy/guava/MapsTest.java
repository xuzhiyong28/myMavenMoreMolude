package xzy.guava;/**
 * Created by Administrator on 2018-10-14.
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Lists;
import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;
import com.utils.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Semaphore;

/**
 * @author xuzhiyong
 * @createDate 2018-10-14-18:09
 */
public class MapsTest {

    public <T extends Object> void printList(T t) {
        System.out.println(JSON.toJSON(t).toString());
    }

    @Test
    public void test1() {
        //快捷构造Map
        Map<String, String> map1 = Maps.newHashMap();
        Map<String, String> map2 = Maps.newTreeMap();
        Map<String, String> map3 = Maps.newConcurrentMap();
        Map<String, String> map4 = Maps.newLinkedHashMap();
    }

    @Test
    public void test2() {
        Map<String, String> map1 = Maps.newHashMap();
        map1.put("name", "xuzy");
        map1.put("age", "12");
        Map<String, String> map2 = Maps.newHashMap(map1);
        map2.put("age", "13");
        printList(map1);
        printList(map2);
    }

    @Test
    public void test3() {
        //比较两个字符串的不同，如果是顺序不同就不算不同
        String leftString = "{\"singleway\":[],\"multiway\":{\"channelSlave\":[{\"name\":\"aa1\",\"channel0name\":\"dd1\",\"id\":\"1111111113\"},{\"name\":\"aa2\",\"channel0name\":\"dd1\",\"id\":\"1111111112\"}],\"channelMaster\":{\"name\":\"aa\",\"channel0name\":\"dd\",\"id\":\"1111111111\"}}}";
        String rightString = "{\"singleway\":[],\"multiway\":{\"channelSlave\":[{\"name\":\"aa2\",\"channel0name\":\"dd1\",\"id\":\"1111111112\"},{\"name\":\"aa1\",\"channel0name\":\"dd1\",\"id\":\"1111111113\"}],\"channelMaster\":{\"name\":\"bb\",\"channel0name\":\"dd\",\"id\":\"1111111111\"}}}";

        Map<String, Object> leftMap = JsonUtils.readJsonToObject(leftString, new TypeReference<Map<String, Object>>() {
        });
        Map<String, Object> rightMap = JsonUtils.readJsonToObject(rightString, new TypeReference<Map<String, Object>>() {
        });
        boolean equal = JsonUtils.compareMap(leftMap, rightMap);
        System.out.println(equal);
    }


    @Test
    public void test4() {
        Map<String, String> map1 = Maps.newHashMap();
        map1.put("name", "xuzy");
        map1.put("age", "12");
        Map<String, String> map2 = Maps.newHashMap();
        map2.put("name", "xuzy2");
        map2.put("age", "12");
        MapDifference<String, String> difference = Maps.difference(map1, map2);
        Map<String, MapDifference.ValueDifference<String>> differenceMap = difference.entriesDiffering();
        for (Iterator diffIterator = differenceMap.entrySet().iterator(); diffIterator.hasNext(); ) {
            Map.Entry entry = (java.util.Map.Entry) diffIterator.next();
            MapDifference.ValueDifference<String> valueDifference = (MapDifference.ValueDifference<String>) entry.getValue();
            System.out.println("left: " + valueDifference.leftValue());
            System.out.println("right: " + valueDifference.rightValue());
        }
    }

    @Test
    public void test5() {
        //Maps.uniqueIndex(Iterable,Function)通常针对的场景是：有一组对象List，它们在某个属性上分别有独一无二的值，
        // 而我们希望能够按照这个属性值将其转成Map
        List list = Lists.newArrayList(new Person(1, "xuzy"), new Person(2, "gaoys"), new Person(3, "chenyx"));
        Map<Integer,Person> map = Maps.uniqueIndex(list, new Function<Person,Integer>() {
            @Override
            public Integer apply(Person person) {
                return person.getId();
            }
        });
        printList(map);
        List<JSONObject> list2 = initData();
        Map<String,JSONObject> map2 = Maps.uniqueIndex(list2, new Function<JSONObject, String>() {
            @Override
            public String apply(JSONObject jsonObject) {
                return jsonObject.getString("name");
            }
        });
        printList(map2);
    }

    @Test
    public void test6(){
        //根据Key的值来过滤
        Map<Integer,String> WEEK  = Maps.newHashMap();
        WEEK.put(1, "Monday");
        WEEK.put(2, "Tuesday");
        WEEK.put(3, "Wednesday");
        WEEK.put(4, "Thursday");
        WEEK.put(5, "Friday");
        WEEK.put(6, "Saturday");
        WEEK.put(7, "Sunday");
        WEEK = Maps.filterKeys(WEEK, new Predicate<Integer>() {
            @Override
            public boolean apply(Integer integer) {
                return integer > 3;
            }
        });
        printList(WEEK);
    }

    @Test
    public void test7(){
        Map<Integer,String> WEEK  = Maps.newHashMap(new HashMap<Integer,String>(){
            {
                put(1, "Monday");
                put(2, "Tuesday");
                put(3, "Wednesday");
                put(4, "Thursday");
                put(5, "Friday");
                put(6, "Saturday");
                put(7, "Sunday");
            }
        });
        WEEK = Maps.filterValues(WEEK, new Predicate<String>() {
            @Override
            public boolean apply(String s) {
                return s.equals("Monday");
            }
        });
        printList(WEEK);
    }



    public List<JSONObject> initData(){
        List<JSONObject> jsonList = Lists.newArrayList();
        JSONObject obj1 = new JSONObject();
        obj1.put("id","1");
        obj1.put("name","xuzy");
        JSONObject obj2 = new JSONObject();
        obj2.put("id","2");
        obj2.put("name","chenyx");
        jsonList.add(obj1);
        jsonList.add(obj2);
        return jsonList;
    }


    public class Person {
        private Integer id;
        private String name;

        public Person(Integer id, String name) {
            this.id = id;
            this.name = name;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
