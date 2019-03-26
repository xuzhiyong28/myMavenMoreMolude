/**
 * Created by Administrator on 2018-04-28.
 */

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mvmoremoduleRedis.service.RedisHyperLogLog;
import com.mvmoremoduleRedis.service.RedisOtherDemo;
import org.junit.Before;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author xuzhiyong
 * @createDate 2018-04-28-17:11
 */
public class Test {

    public ApplicationContext applicationContext;

    public RedisTemplate redisTemplate;
    @Before
    public void initApp(){
        applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
    }

    @org.junit.Test
    public void test1(){
        RedisHyperLogLog redisHyperLogLog = applicationContext.getBean(RedisHyperLogLog.class);
        redisHyperLogLog.init();
    }

    @org.junit.Test
    public void test2(){
        ClassPathResource classPathResource = new ClassPathResource("applicationContext.xml");
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(factory);
        xmlBeanDefinitionReader.loadBeanDefinitions(classPathResource);
    }

    @org.junit.Test
    public void testRedis1(){
        redisTemplate = (RedisTemplate)applicationContext.getBean("redisTemplate");
        JSONObject object = new JSONObject();
        object.put("name","xuzhiyong");
        object.put("age","28");
        redisTemplate.opsForValue().set("xuzy",object,1000, TimeUnit.SECONDS);
        JSONObject redisObj = (JSONObject) redisTemplate.opsForValue().get("xuzy");
        System.out.println(redisObj);
    }


    @org.junit.Test
    public void redisOther1(){
        RedisOtherDemo redisOtherDemo = (RedisOtherDemo)applicationContext.getBean("redisOtherDemo");
        String key = "testKey";
        try {
            List<String> list = redisOtherDemo.getCacheSave2(key,10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
