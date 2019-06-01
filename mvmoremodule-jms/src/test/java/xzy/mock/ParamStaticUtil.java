package xzy.mock;

import com.google.common.collect.Lists;

import java.util.List;

public class ParamStaticUtil {

    public static List<String> getDataBySQL(){
        System.out.println("===========模拟从数据库取出值===========");
        List<String> list = Lists.newArrayList("a","b","c");
        return list;
    }
}
