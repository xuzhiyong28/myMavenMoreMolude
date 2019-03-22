package com.mvmoremodulePattern.proxy.dtproxy;/**
 * Created by Administrator on 2018-05-13.
 */

import com.mvmoremodulePattern.proxy.staticproxy.*;

/**
 * @author xuzhiyong
 * @createDate 2018-05-13-13:45
 * 动态代理
 */
public class Main {
    public static void main(String agrs[]){
        ManCompany manCompany = new SaleManThingCompany();
        ProxyCompany proxy = new ProxyCompany();
        proxy.setObject(manCompany);
        ManCompany subject = (ManCompany) proxy.newProxyInstance();
        subject.saleManThing();

        proxy.setObject(new SaleMan2ThingCompany());
        ManCompany subject2 = (ManCompany) proxy.newProxyInstance();
        subject2.saleManThing();

        WeManCompany weManCompany = new SaleWeManThingCompany();
        proxy.setObject(weManCompany);
        WeManCompany weManCompany1 = (WeManCompany)proxy.newProxyInstance();
        weManCompany1.saleWeManThing();

    }
}
