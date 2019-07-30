package xzy.java8;

/**
 * @author xuzhiyong
 * @createDate 2019-07-30-22:33
 */
//@FunctionalInterface的作用是注明是函数式接口，只能有一个接口方法，如果录入第二个接口方法会报错
@FunctionalInterface
public interface Converter<F,T> {
    T convert(F from);
}
