package com.xzy.aop.service;

import com.xzy.aop.UserService;

public class UserServiceImpl implements UserService {
    public String createUser(String firstName, String lastName, int age) {
        System.out.println("========UserService-createOrder========");
        return "success";
    }

    public String queryUser() {
        System.out.println("========UserService-queryUser========");
        return "success";
    }
}
