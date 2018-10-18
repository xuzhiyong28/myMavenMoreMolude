package xzy.guava;/**
 * Created by Administrator on 2018-10-18.
 */

import com.google.common.base.Splitter;
import org.junit.Test;

import java.util.List;

/**
 * @author xuzhiyong
 * @createDate 2018-10-18-22:14
 */
public class SplitterTest {
    @Test
    public void test1() {
        String str = "小小,妈妈,爸爸,爷爷,奶奶";
        Splitter splitter = Splitter.on(",");
        List<String> list = splitter.splitToList(str);
        System.out.println(list);
    }

    @Test
    public void test2() {
        //忽略空字符
        String str1 = "a,b,c,d,,f,g";
        List<String> list1 = Splitter.on(",").omitEmptyStrings().splitToList(str1);
        System.out.println(list1);
    }

    @Test
    public void test3(){
        //忽略空字符且去除字符串前后空格
        String str2 = "a,b,c,d,,f,  g  ";
        List<String> list = Splitter.on(",").omitEmptyStrings().trimResults().splitToList(str2);
        System.out.println(list);
    }
}
