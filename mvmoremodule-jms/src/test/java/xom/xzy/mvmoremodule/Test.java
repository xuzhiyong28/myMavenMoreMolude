package xom.xzy.mvmoremodule;/**
 * Created by Administrator on 2018-03-30.
 */

import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;

import java.util.Random;

/**
 * @author xuzhiyong
 * @createDate 2018-03-30-19:16
 */
public class Test {

    @org.junit.Test
    public void test1(){
        ClassPathResource classPathResource = new ClassPathResource("applicationContext.xml");
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(factory);
        xmlBeanDefinitionReader.loadBeanDefinitions(classPathResource);
    }

    @org.junit.Test
    public void test2(){
        for(int i = 1 ; i <= 288; i++){
            System.out.println(RandomUtils.nextInt(10000000,50000000));
        }
    }
}
