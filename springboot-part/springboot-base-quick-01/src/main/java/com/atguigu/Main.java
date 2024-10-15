package com.atguigu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// @EnableAutoConfiguration 自动加载配置类
// @ComponentScan 默认是当前类所在的包
public class Main {
    public static void main(String[] args) {
        // 创建ioc容器，加载配置 2.启动内置的web服务器
        SpringApplication.run(Main.class, args);
    }
}