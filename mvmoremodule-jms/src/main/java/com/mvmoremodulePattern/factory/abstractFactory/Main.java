package com.mvmoremodulePattern.factory.abstractFactory;/**
 * Created by Administrator on 2018-04-26.
 */


import com.mvmoremodulePattern.factory.Hamburger;
import com.mvmoremodulePattern.factory.Pizza;

/**
 * @author xuzhiyong
 * @createDate 2018-04-26-13:54
 */
public class Main  {
    public static void main(String agrs[]){
        Factory factory = new FamilyFactory();
        Pizza CcheesePizza = factory.createPizza();
        Hamburger familyHamburger = factory.createHamburger();
    }
}
