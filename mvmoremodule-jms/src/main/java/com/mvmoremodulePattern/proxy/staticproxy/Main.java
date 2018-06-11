package com.mvmoremodulePattern.proxy.staticproxy;/**
 * Created by Administrator on 2018-05-13.
 */



/**
 * @author xuzhiyong
 * @createDate 2018-05-13-13:31
 */
public class Main {
    public static void main(String agrs[]){
        ManCompany manCompany = new SaleManThingCompany();
        ListonProxy listonProxy = new ListonProxy(manCompany);
        listonProxy.saleManThing();
    }
}
