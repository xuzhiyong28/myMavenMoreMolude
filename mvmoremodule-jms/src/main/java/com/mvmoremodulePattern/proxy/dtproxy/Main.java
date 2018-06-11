package com.mvmoremodulePattern.proxy.dtproxy;/**
 * Created by Administrator on 2018-05-13.
 */

import com.mvmoremodulePattern.proxy.staticproxy.ManCompany;
import com.mvmoremodulePattern.proxy.staticproxy.SaleManThingCompany;
import com.mvmoremodulePattern.proxy.staticproxy.SaleWeManThingCompany;
import com.mvmoremodulePattern.proxy.staticproxy.WeManCompany;

/**
 * @author xuzhiyong
 * @createDate 2018-05-13-13:45
 */
public class Main {
    public static void main(String agrs[]){
        ManCompany manCompany = new SaleManThingCompany();
        ListenCompany listenCompany = new ListenCompany();
        listenCompany.setObject(manCompany);
        ManCompany subject = (ManCompany) listenCompany.newProxyInstance();
        subject.saleManThing();


        WeManCompany weManCompany = new SaleWeManThingCompany();
        listenCompany.setObject(weManCompany);
        WeManCompany weManCompany1 = (WeManCompany)listenCompany.newProxyInstance();
        weManCompany1.saleWeManThing();
    }
}
