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

        //获取其中一个注解,包括父类继承的
        SupperAnnotation supperAnnotation = clazz.getAnnotation(SupperAnnotation.class);
        System.out.println(supperAnnotation);

        //获取类上的注解，不包括父类注解的，下面的会返回空，因为父类上的注解没法被获取
        SupperAnnotation declaredAnnotation = clazz.getDeclaredAnnotation(SupperAnnotation.class);
        System.out.println(declaredAnnotation);

        //获取类上的注解，不包括父类注解的
        Annotation[] declaredAnnotations = clazz.getDeclaredAnnotations();
        for (Annotation annotation : declaredAnnotations) {
            System.out.println(annotation);
        }


        SubAnnotation[] declaredAnnotationsByType = clazz.getDeclaredAnnotationsByType(SubAnnotation.class);
        System.out.println(declaredAnnotationsByType);


    }

    @SupperAnnotation
    private static class Supper {
    }

    @SubAnnotation
    private static class Sub extends Supper {
    }

}
