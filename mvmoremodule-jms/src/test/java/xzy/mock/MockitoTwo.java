package xzy.mock;

import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.LinkedList;
import java.util.List;

/****
 * 单纯的mockito没办法mock静态类，所以用PowerMock
 * Mock能做到的 PowderMock好像都能做到
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ParamStaticUtil.class})
public class MockitoTwo {

    @Test
    public void test(){
        PowerMockito.mockStatic(ParamStaticUtil.class);
        Mockito.when(ParamStaticUtil.getDataBySQL()).thenReturn(Lists.newArrayList("1","2"));
        List<String> data = ParamStaticUtil.getDataBySQL();
        System.out.println(data);
    }

    @Test
    public void test2(){
        LinkedList linkedList = PowerMockito.mock(LinkedList.class);
        PowerMockito.when(linkedList.get(0)).thenReturn("first");
        System.out.println(linkedList.get(0));
    }
}
