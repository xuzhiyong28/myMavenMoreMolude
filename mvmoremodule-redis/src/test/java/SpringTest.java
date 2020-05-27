import com.springTest.AppConfig;
import com.springTest.BeanA;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.ClassRelativeResourceLoader;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.IOException;
import java.util.Arrays;

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

    /***
     * spring 资源加载器
     * @throws IOException
     */
    @Test
    public void resourceTest() throws IOException {
        ResourceLoader classResourceLoader = new ClassRelativeResourceLoader(BeanA.class);
        Resource resource = classResourceLoader.getResource("AppConfig.class");
        System.out.println(resource.getFile().getPath());

        ResourceLoader fileResourceLoader = new FileSystemResourceLoader();
        Resource fileResource = fileResourceLoader.getResource("D:\\workspack\\myMavenMoreMolude\\mvmoremodule-redis\\src\\main\\resources\\test.lua");
        System.out.println(fileResource);

        //这种最好用，支持通配符
        //classpath: 用于加载类路径（包括jar包）中的一个且仅一个资源
        //classpath*: 用于加载类路径（包括jar包）中的所有匹配的资源
        PathMatchingResourcePatternResolver pathMatchingResourceLoader = new PathMatchingResourcePatternResolver();
        Resource resource1 = pathMatchingResourceLoader.getResource("classpath:bean.xml");
        System.out.println(String.format("资源路径= %s", resource1.getFile().getPath()));
        Resource resource2 = pathMatchingResourceLoader.getResource("classpath:com/springTest/BeanA.class");
        System.out.println(String.format("资源路径= %s", resource2.getFile().getPath()));
        Resource[] resource3 = pathMatchingResourceLoader.getResources("classpath*:com/*/*.class");
        Arrays.stream(resource3).forEach(resource4 -> {
            try {
                System.out.println(String.format("资源路径= %s", resource4.getFile().getPath()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
