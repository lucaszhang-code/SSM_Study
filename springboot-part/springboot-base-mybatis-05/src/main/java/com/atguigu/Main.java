package com.atguigu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.atguigu.mapper") // 指定mapper接口所在的位置
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}