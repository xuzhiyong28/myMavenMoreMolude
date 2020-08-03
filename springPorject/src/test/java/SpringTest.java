import com.xzy.annotation.AppConfig;
import com.xzy.annotation.bean.BeanD;
import com.xzy.aop.OrderService;
import com.xzy.aop.UserService;
import com.xzy.bean.BeanB;
import com.xzy.bean.BeanC;
import com.xzy.bean.SpringCycleBean;
import com.xzy.listener.MyApplicationEvent;
import com.xzy.postProcessor.MyInstantiationAwareBeanPostProcessor;
import com.xzy.rwDynamic.DBContextHolder;
import com.xzy.rwDynamic.DbType;
import com.xzy.tx.TxService;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class SpringTest {
    @Test
    public void test1() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        System.out.println("context 启动成功");
        SpringCycleBean bean = context.getBean(SpringCycleBean.class);
        bean.doSing();
    }

    @Test
    public void testAnnotataion() {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        BeanD bean = context.getBean(BeanD.class);
        context.getBean(SpringCycleBean.class);

    }


    /***
     * spring 加载bean
     */
    @Test
    public void test2() {
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
        BeanB bean = factory.getBean(BeanB.class);
        System.out.println(bean);
    }

    @Test
    public void test3() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        System.out.println("context 启动成功");
        BeanC bean = context.getBean(BeanC.class);
        System.out.println(bean);
    }


    @Test
    public void testAop() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext-aop.xml");
        UserService userService = context.getBean(UserService.class);
        OrderService orderService = context.getBean(OrderService.class);
        userService.createUser("Tom", "Cruise", 55);
        userService.queryUser();
        orderService.createOrder("Leo", "随便买点什么");
        orderService.queryOrder("Leo");
    }

    @Test
    public void testAopAnnotation() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext-aop-annotation.xml");
        UserService userService = context.getBean(UserService.class);
        userService.queryUser();
    }


    @Test
    public void testAopConfig() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext-aop-config.xml");
        UserService userService = context.getBean(UserService.class);
        OrderService orderService = context.getBean(OrderService.class);
        userService.createUser("Tom", "Cruise", 55);
        userService.queryUser();
        orderService.createOrder("Leo", "随便买点什么");
        orderService.queryOrder("Leo");
    }

    @Test
    public void testTx() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext-tx.xml");
        TxService txService = context.getBean(TxService.class);
        txService.updateData();
    }

    @Test
    public void testTx2() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext-tx.xml");
        TxService txService = context.getBean(TxService.class);
        txService.updateByThread();
    }


    @Test
    public void testJavaTx() {
        DataSource dataSource = TestUtils.getDataSource();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        //创建物管理器
        DataSourceTransactionManager txManager = new DataSourceTransactionManager();
        txManager.setDataSource(dataSource);
        //定义事物属性
        DefaultTransactionDefinition txDefinition = new DefaultTransactionDefinition();
        txDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        //开启事务
        TransactionStatus txStatus = txManager.getTransaction(txDefinition);
        try {
            jdbcTemplate.update("INSERT INTO `book`( `book`, `price`) VALUES (?,?)", "书", 12);
            jdbcTemplate.update("INSERT INTO `user`( `name`, `age`) VALUES (?,?)", "名字1", 12);
            int i = 1 / 0;
            txManager.commit(txStatus);
        } catch (Exception e) {
            txManager.rollback(txStatus);
            e.printStackTrace();
        }
    }


    @Test
    public void testJdbcTempleThread() {
        DataSource dataSource = TestUtils.getDataSource();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        //List<Map<String, Object>> maps = jdbcTemplate.queryForList("select * from user");
        //List<Map<String, Object>> maps2 = jdbcTemplate.queryForList("select * from user");
        for (int i = 0; i < 10000; i++) {
            jdbcTemplate.update("INSERT INTO `single_table` (`key1`, `key2`, `key3`, `key_part1`, `key_part2`, `key_part3`, `common_field`) VALUES (?,?,?,?,?,?,?)"
                    , getRandomString(3), i, getRandomString(4), getRandomString(5), getRandomString(5), getRandomString(5), getRandomString(10));
        }
    }


    //length用户要求产生字符串的长度
    public static String getRandomString(int length){
        String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<length;i++){
            int number=random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    @Test
    public void listenerTest() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext-listener.xml");
        //创建自定义的事件
        MyApplicationEvent myApplicationEvent = new MyApplicationEvent("test event!");
        //发布事件
        context.publishEvent(myApplicationEvent);

    }

    /***
     * 读写分离 动态数据库
     */
    @Test
    public void testwrDynamic() {
        //设置当前线程使用主线程
        DBContextHolder.set(DbType.MASTER.getType());
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext-w-r-dynamic.xml");
        JdbcTemplate jdbcTemplate = context.getBean(JdbcTemplate.class);
        jdbcTemplate.queryForList("select * from user");
    }

}
