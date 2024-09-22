package com.itguigu.ioc_04;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JavaBean {


    // 方案一,直接赋值
    private String name = "Lucas";

    // 方案二,value注解，读取外部配置
    // 引入外部配置
    // 默认值@Value("${key:value}")
    @Value("20")
    private int age;

    @Value("${jdbc.username:admin}")
    private String userName;
    @Value("${jdbc.password:000000}")
    private String password;

    @Override
    public String toString() {
        return "JavaBean{" +
                "password='" + password + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}
