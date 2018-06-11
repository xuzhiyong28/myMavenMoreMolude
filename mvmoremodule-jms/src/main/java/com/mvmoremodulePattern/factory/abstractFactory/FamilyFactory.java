package com.mvmoremodulePattern.factory.abstractFactory;/**
 * Created by Administrator on 2018-04-26.
 */


import com.mvmoremodulePattern.factory.Hamburger;
import com.mvmoremodulePattern.factory.Pizza;

/**
 * @author xuzhiyong
 * @createDate 2018-04-26-13:54
 */
public class FamilyFactory implements  Factory {
    @Override
    public Pizza createPizza() {
        return new CheesePizza();
    }

    @Override
    public Hamburger createHamburger() {
        return new FamilyHamburger();
    }
}
