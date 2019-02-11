package com.mvmoremodulePattern.proxy.staticproxy;
/**
 * @author xuzhiyong
 * @createDate 2018-05-13-13:28
 */
public class SaleMan2ThingCompany implements ManCompany{
    @Override
    public void saleManThing() {
        System.out.println("===卖男性需要的东西===");
    }
}
