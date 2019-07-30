package xzy.guava;

import com.google.common.base.Preconditions;
import org.junit.Test;

/**
 * @author xuzhiyong
 * @createDate 2019-07-30-22:51
 * Preconditions是guava提供的用于进行代码校验的工具类，其中提供了许多重要的静态校验方法，用来简化我们工作或开发中对代码的校验或预 处理，
 * 能够确保代码符合我们的期望，并且能够在不符合校验条件的地方，准确的为我们显示出问题所在
 */
public class PreconditionsTest {

    @Test
    public void test(){
        //Preconditions.checkArgument(true);
        Preconditions.checkArgument(false,"当第一个参数是false的时候返回报错");
    }

    @Test
    public void test2(){
        //Preconditions.checkState(true);
        Preconditions.checkState(false,"当第一个参数是false的时候返回报错");
    }

    @Test
    public void test3(){
        //Preconditions.checkNotNull(null,"当为空的时候抛出异常");
        try{
            Preconditions.checkNotNull(null,"当为空的时候抛出异常");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void test4(){
        //checkElementIndex( int index, int size, @Nullable String desc)：校验元素的索引值是否有效，index大于等于0小于size，在无效时显示错误描述信息。
        //Preconditions.checkElementIndex(10,4,"10这个索引大于4报错");
        Preconditions.checkElementIndex(4,10,"4大于0小于10不会报错");
    }


    @Test
    public void test5(){
        //checkPositionIndexes(int start, int end, int size)校验大于等于start，小于end的list的长度是否为size,否则报错
        Preconditions.checkPositionIndexes(1,10,5);
    }


}
