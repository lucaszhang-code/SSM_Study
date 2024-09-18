package com.itguigu.ioc_02;

public class UserService {
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }


    private UserDao userDao;
    private int age;
    private String name;

    public UserService(int age , String name ,UserDao userDao) {
        this.userDao = userDao;
        this.age = age;
        this.name = name;
    }
}
