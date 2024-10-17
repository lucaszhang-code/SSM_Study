package com.atguigu.test;

import com.atguigu.mapper.UserMapper;
import com.atguigu.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MyBatisPlusVersionTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void test() {
        User user = userMapper.selectById(1);
        User user1 = userMapper.selectById(1);

        user.setAge(20);
        user1.setAge(30);

        userMapper.updateById(user);
        userMapper.updateById(user1);
    }

    @Test
    public void test2() {
        userMapper.delete(null); // 全表删除
    }
}
