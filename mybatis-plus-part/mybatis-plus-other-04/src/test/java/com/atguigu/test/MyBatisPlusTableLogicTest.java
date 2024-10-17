package com.atguigu.test;

import com.atguigu.mapper.UserMapper;
import com.atguigu.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class MyBatisPlusTableLogicTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void test() {
        int i = userMapper.deleteById(1);

        List<User> users = userMapper.selectList(null);

    }
}
