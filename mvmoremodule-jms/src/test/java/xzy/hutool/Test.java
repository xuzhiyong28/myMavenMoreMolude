package xzy.hutool;

import cn.hutool.core.util.ObjectUtil;

public class Test {
    @org.junit.Test
    public void test1(){
        Model model = new Model();
        model.setAge(10);
        model.setName("xuzy");
        Model copyModel = ObjectUtil.cloneByStream(model);
    }
}
