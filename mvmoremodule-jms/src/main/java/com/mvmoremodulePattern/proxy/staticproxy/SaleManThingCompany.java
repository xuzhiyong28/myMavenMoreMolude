package com.mvmoremodulePattern.proxy.staticproxy;/**
 * Created by Administrator on 2018-05-13.
 */

/**
 * @author xuzhiyong
 * @createDate 2018-05-13-13:28
 */
public class SaleManThingCompany implements ManCompany {
    @Override
    public void saleManThing() {
        System.out.println("===卖男性需要的东西===");
    }
}
