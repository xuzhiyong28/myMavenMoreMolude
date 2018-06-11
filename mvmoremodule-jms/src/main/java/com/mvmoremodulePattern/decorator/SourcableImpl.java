package com.mvmoremodulePattern.decorator;/**
 * Created by Administrator on 2018-05-14.
 */

/**
 * @author xuzhiyong
 * @createDate 2018-05-14-9:14
 */
public class SourcableImpl implements Sourcable {
    @Override
    public void operation() {
        System.out.println("===具体方法===");
    }
}
