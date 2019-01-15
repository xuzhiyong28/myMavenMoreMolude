/**
 * Created by Administrator on 2018-04-28.
 */

import com.mvmoremoduleRedis.service.RedisHyperLogLog;
import org.junit.Before;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author xuzhiyong
 * @createDate 2018-04-28-17:11
 */
public class Test {

    public ApplicationContext applicationContext;
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


}
