package com.xzy.aop.service;

import com.xzy.aop.OrderService;

public class OrderServiceImpl implements OrderService {
    public String createOrder(String username, String product) {
        System.out.println("========orderService-createOrder========");
        return "success";
    }

    public String queryOrder(String username) {
        System.out.println("========orderService-queryOrder========");
        return "success";
    }
}
