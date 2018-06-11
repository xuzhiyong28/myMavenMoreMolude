package com.mvmoremodulePattern.factory.abstractFactory;


import com.mvmoremodulePattern.factory.Hamburger;
import com.mvmoremodulePattern.factory.Pizza;

/**
 * Created by Administrator on 2018-04-26.
 */
public interface Factory {
    public Pizza createPizza();
    public Hamburger createHamburger();
}
