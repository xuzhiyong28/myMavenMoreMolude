package xzy.mock;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.InOrder;

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

    @Test
    public void test5(){
        LinkedList mockedList = mock(LinkedList.class);
        mockedList.add("once");

        mockedList.add("twice");
        mockedList.add("twice");

        mockedList.add("three");
        mockedList.add("three");
        mockedList.add("three");

        //调用add("once")的次数为1次
        verify(mockedList,times(1)).add("once");
        verify(mockedList,times(2)).add("twice");
        verify(mockedList,times(3)).add("three");

        verify(mockedList, atLeastOnce()).add("three"); //至少执行一次
        verify(mockedList, atLeast(2)).add("three"); //最少执行两次
        verify(mockedList, atMost(5)).add("three"); //最多执行5次

        verify(mockedList,never()).add("xuzhiyong"); //从没有执行过add("xuzhiyong")
    }

    @Test
    public void test6(){
        LinkedList mockedList = mock(LinkedList.class);
        //设置当执行clear时抛出RuntimeException错误
        doThrow(new RuntimeException("错误")).when(mockedList).clear();
        mockedList.clear();
    }

    @Test
     public void test7(){
        List singleMock = mock(List.class);
        singleMock.add("was added first");
        singleMock.add("was added second");
        InOrder inorder = inOrder(singleMock); //判断是否按顺序执行过方法
        inorder.verify(singleMock).add("was added first");
        inorder.verify(singleMock).add("was added second");
     }

     @Test
     public void test8(){
         LinkedList mockedList = mock(LinkedList.class);
         mockedList.add("one");
         //mockedList.add("two"); 开发这个后，add("two")下面没有校验 所以会报错
         verify(mockedList).add("one");;
         verifyNoMoreInteractions(mockedList); //用来验证传入的这些mock对象是否存在没有验证过的调用方法
     }

     @Test
     public void test9(){
         LinkedList mockedList = mock(LinkedList.class);
         //设置第一次执行抛异常 第二次执行返回true
         when(mockedList.add("one")).thenThrow(new RuntimeException()).thenReturn(true);
         mockedList.add("one");
         mockedList.add("one");
     }


}
