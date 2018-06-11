package com.mvmoremodulePattern.adapter.objectadapter;/**
 * Created by Administrator on 2018-04-26.
 */

/**
 * @author xuzhiyong
 * @createDate 2018-04-26-15:51
 */
public interface Target {
    /**
     * 这是源类也有的方法simpleOperation1
     */
    void simpleOperation1();

    /**
     * 这是源类没有的方法simpleOperation2
     */
    void simpleOperation2();
}
