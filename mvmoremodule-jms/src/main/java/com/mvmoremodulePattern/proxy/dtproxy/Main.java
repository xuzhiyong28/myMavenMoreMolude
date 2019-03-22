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
        ProxyCompany listenCompany = new ProxyCompany();
        listenCompany.setObject(manCompany);
        ManCompany subject = (ManCompany) listenCompany.newProxyInstance();
        subject.saleManThing();

        listenCompany.setObject(new SaleMan2ThingCompany());
        ManCompany subject2 = (ManCompany) listenCompany.newProxyInstance();
        subject2.saleManThing();

        WeManCompany weManCompany = new SaleWeManThingCompany();
        listenCompany.setObject(weManCompany);
        WeManCompany weManCompany1 = (WeManCompany)listenCompany.newProxyInstance();
        weManCompany1.saleWeManThing();

    }
}
