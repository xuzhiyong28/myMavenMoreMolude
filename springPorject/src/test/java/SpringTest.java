import com.xzy.annotation.AppConfig;
import com.xzy.annotation.bean.BeanD;
import com.xzy.aop.OrderService;
import com.xzy.aop.UserService;
import com.xzy.bean.BeanC;
import com.xzy.bean.SpringCycleBean;
import com.xzy.postProcessor.MyInstantiationAwareBeanPostProcessor;
import com.xzy.tx.TxService;
import org.junit.Test;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

public class SpringTest {
    @Test
    public void test1(){
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        System.out.println("context 启动成功");
        SpringCycleBean bean = context.getBean(SpringCycleBean.class);
        bean.doSing();
    }

    @Test
    public void testAnnotataion(){
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        BeanD bean = context.getBean(BeanD.class);
        context.getBean(SpringCycleBean.class);

    }


    /***
     * spring 加载bean
     */
    @Test
    public void test2(){
        //获取资源
        ClassPathResource resource = new ClassPathResource("applicationContext.xml");
        //获取BeanFactory
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        factory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());
        //factory.addBeanPostProcessor(new AutowiredAnnotationBeanPostProcessor());
        //BeanDefinition解析器
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        //解析资源
        reader.loadBeanDefinitions(resource);
        SpringCycleBean bean = factory.getBean(SpringCycleBean.class);
        System.out.println(bean);
    }

    @Test
    public void test3(){
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        System.out.println("context 启动成功");
        BeanC bean = context.getBean(BeanC.class);
        System.out.println(bean);
    }


    @Test
    public void testAop(){
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext-aop.xml");
        UserService userService = context.getBean(UserService.class);
        OrderService orderService = context.getBean(OrderService.class);
        userService.createUser("Tom", "Cruise", 55);
        userService.queryUser();
        orderService.createOrder("Leo", "随便买点什么");
        orderService.queryOrder("Leo");
    }

    @Test
    public void testAopAnnotation(){
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext-aop-annotation.xml");
        UserService userService = context.getBean(UserService.class);
        userService.queryUser();
    }


    @Test
    public void testAopConfig(){
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext-aop-config.xml");
        UserService userService = context.getBean(UserService.class);
        OrderService orderService = context.getBean(OrderService.class);
        userService.createUser("Tom", "Cruise", 55);
        userService.queryUser();
        orderService.createOrder("Leo", "随便买点什么");
        orderService.queryOrder("Leo");
    }

    @Test
    public void testTx(){
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext-tx.xml");
        TxService txService = context.getBean(TxService.class);
        txService.queryUser();
    }

}
