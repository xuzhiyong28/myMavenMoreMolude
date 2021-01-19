package com.mvmoremodulePattern.proxy.factoryproxy;

import com.mvmoremodulePattern.proxy.staticproxy.ManCompany;
import com.mvmoremodulePattern.proxy.staticproxy.SaleManThingCompany;

public class Main {
    public static void main(String[] args){
        ManCompany manCompany = new SaleManThingCompany();
        ProxyInvocationHandler<ManCompany> proxy = new ProxyInvocationHandler<>(manCompany);
        ProxyFactory<ManCompany> proxyFactory = new ProxyFactory<>(ManCompany.class);
        ManCompany obj = proxyFactory.newInstance(proxy);
        obj.saleManThing();
    }
}
