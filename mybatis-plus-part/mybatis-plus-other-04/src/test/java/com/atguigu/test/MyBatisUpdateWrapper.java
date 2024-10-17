package com.atguigu.test;

import com.atguigu.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MyBatisUpdateWrapper {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void test(){

    }
}
