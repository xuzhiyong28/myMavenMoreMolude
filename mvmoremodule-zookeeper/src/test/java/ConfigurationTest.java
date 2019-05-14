import com.xzy.curator.configucenter.DefaultConfigurationCenter;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * @author xuzhiyong
 * @createDate 2019-05-14-22:20
 */
public class ConfigurationTest {





    @Test
    public void test(){
        DefaultConfigurationCenter.initCuratorClient();
        try {
            TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
