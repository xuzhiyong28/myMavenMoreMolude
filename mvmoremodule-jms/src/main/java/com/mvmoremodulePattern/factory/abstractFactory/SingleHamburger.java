package com.mvmoremodulePattern.factory.abstractFactory;/**
 * Created by Administrator on 2018-04-26.
 */


import com.mvmoremodulePattern.factory.Hamburger;

/**
 * @author xuzhiyong
 * @createDate 2018-04-26-13:52
 */
public class SingleHamburger implements Hamburger {
    @Override
    public void create() {
        System.out.println("单人套餐汉堡");
    }
}
