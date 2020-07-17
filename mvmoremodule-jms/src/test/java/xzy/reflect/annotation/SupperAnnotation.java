package xzy.reflect.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Target(ElementType.TYPE)
public @interface  SupperAnnotation {
    String value() default "SupperAnnotation";
}
