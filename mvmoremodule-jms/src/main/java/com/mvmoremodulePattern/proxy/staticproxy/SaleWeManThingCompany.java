package com.mvmoremodulePattern.proxy.staticproxy;/**
 * Created by Administrator on 2018-05-13.
 */

/**
 * @author xuzhiyong
 * @createDate 2018-05-13-13:41
 */
public class SaleWeManThingCompany implements WeManCompany {
    @Override
    public void saleWeManThing() {
        System.out.println("===卖女性需要的东西===");
    }
}
