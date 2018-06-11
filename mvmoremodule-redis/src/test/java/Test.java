/**
 * Created by Administrator on 2018-04-28.
 */

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

/**
 * @author xuzhiyong
 * @createDate 2018-04-28-17:11
 */
public class Test {

    @org.junit.Test
    public void test1(){
        //ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        String kk = "1345627";
        System.out.println(kk.indexOf("2") + ".." + kk.lastIndexOf("2"));

    }

    @org.junit.Test
    public void test2(){
        ClassPathResource classPathResource = new ClassPathResource("applicationContext.xml");
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(factory);
        xmlBeanDefinitionReader.loadBeanDefinitions(classPathResource);

    }


}
