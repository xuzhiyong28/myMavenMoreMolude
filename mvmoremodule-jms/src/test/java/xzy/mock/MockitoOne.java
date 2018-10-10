package xzy.mock;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.ArgumentMatcher;

import javax.xml.ws.RequestWrapper;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.*;

public class MockitoOne {
    @Test
    public void test() {
        List mockList = mock(List.class);
        mockList.add("one");
        mockList.clear();
        verify(mockList).add("one");
        verify(mockList).clear();
    }

    @Test
    public void test2() {
        LinkedList mockedList = mock(LinkedList.class);
        when(mockedList.get(0)).thenReturn("first");
        when(mockedList.get(1)).thenReturn(new RuntimeException());
        System.out.println(mockedList.get(0));
        System.out.println(mockedList.get(1));
        System.out.println(mockedList.get(999));
        verify(mockedList).get(999); //判断是否有执行过get(999)
    }

    @Test
    public void test3() {
        LinkedList mockedList = mock(LinkedList.class);
        when(mockedList.get(anyInt())).thenReturn("element");
        Assert.assertEquals("element", mockedList.get(2));
    }

    @Test
    public void test4() {
        List mockList = mock(List.class);
        //when(mockList.addAll(argThat(new IsListOfTwoEleArgumentMatcher()))).thenReturn(true);
        //设置一个自定义参数构造器，限定了addAll的参数数量是2两个，不然会报错
        when(mockList.addAll(argThat(new ArgumentMatcher<List>() {
            @Override
            public boolean matches(List list) {
                return list.size() == 2;
            }
        }))).thenReturn(true);
        Assert.assertEquals(true, mockList.addAll(Arrays.asList("1", "2")));
    }

}
