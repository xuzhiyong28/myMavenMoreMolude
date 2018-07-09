package xom.xzy.mvmoremodule;/**
 * Created by Administrator on 2018-03-30.
 */

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;

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
        float k1 = (float) 0.1;
        float k2 = (float) 0.2;
        System.out.println(k1 + k2);
    }
}
