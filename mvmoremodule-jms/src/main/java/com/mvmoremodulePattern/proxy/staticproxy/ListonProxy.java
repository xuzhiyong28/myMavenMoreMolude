package com.mvmoremodulePattern.proxy.staticproxy;/**
 * Created by Administrator on 2018-05-13.
 */

/**
 * @author xuzhiyong
 * @createDate 2018-05-13-13:29
 * 代理类，代理类代理具体公司贩卖物品并提供额外服务
 * 静态代理缺点 ： 扩展性和维护性差
 */
public class ListonProxy implements ManCompany {
    private ManCompany manCompany;

    public ListonProxy(ManCompany manCompany){
        this.manCompany = manCompany;
    }

    @Override
    public void saleManThing() {
        beforeSale();
        manCompany.saleManThing();
        afterSale();
    }

    public void beforeSale(){
        System.out.println("===提供包装服务===");
    }

    public void afterSale(){
        System.out.println("===提供包退服务===");
    }



}
