package xzy.reflect.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(ElementType.TYPE)
public @interface SubAnnotation {
    String value() default "SubAnnotation";
}
