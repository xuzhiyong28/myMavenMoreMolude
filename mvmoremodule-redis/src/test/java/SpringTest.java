import com.springTest.AppConfig;
import com.springTest.BeanA;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author xuzhiyong
 * @createDate 2019-12-17-22:11
 */
public class SpringTest {
    @org.junit.Test
    public void testSpring(){
        AnnotationConfigApplicationContext annotationConfigApplicationContext =
                new AnnotationConfigApplicationContext(AppConfig.class);
        System.out.println(annotationConfigApplicationContext.getBean(BeanA.class));
    }
}
