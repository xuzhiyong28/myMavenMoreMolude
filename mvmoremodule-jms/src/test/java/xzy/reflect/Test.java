package xzy.reflect;

import xzy.reflect.annotation.SubAnnotation;
import xzy.reflect.annotation.SupperAnnotation;

import java.lang.annotation.Annotation;

public class Test {
    @org.junit.Test
    public void test0(){
        Class<?> clazz = Sub.class;
        //getAnnotations会获取当前实例上所有注解实例，包括继承获得的注解
        Annotation[] annotations = clazz.getAnnotations();
        for (Annotation annotation : annotations) {
            System.out.println(annotation.toString());
        }
    }

    @SupperAnnotation
    private static class Supper {
    }

    @SubAnnotation
    private static class Sub extends Supper {
    }

}
