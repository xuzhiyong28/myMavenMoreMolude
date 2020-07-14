package com.xzy.aop;

public interface OrderService {
    String createOrder(String username, String product);
    String queryOrder(String username);
}
