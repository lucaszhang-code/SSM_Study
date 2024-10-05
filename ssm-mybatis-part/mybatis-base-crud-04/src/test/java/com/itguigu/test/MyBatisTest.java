package com.itguigu.test;

import com.itguigu.mapper.UserMapper;
import com.itguigu.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MyBatisTest {

    private SqlSession sqlSession;
    private UserMapper userMapper;

    @BeforeEach
    public void before() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(resourceAsStream);
        sqlSession = build.openSession();
        userMapper = sqlSession.getMapper(com.itguigu.mapper.UserMapper.class);
    }

    @AfterEach
    public void clean(){
        sqlSession.commit();
        sqlSession.close();
    }


    @Test
    public void testInsert() {
        User user = new User();
        user.setUsername("Tony");
        user.setPassword("123456");
        int row = userMapper.insert(user);
        System.out.println(row);
    }

    @Test
    public void testUpdate() {

    }

    @Test
    public void testDelete() {
        int row = userMapper.delete(1);
        System.out.println(row);
    }

    @Test
    public void selectBId() {
        User user = userMapper.selectById(1);
        System.out.println(user);

    }

    @Test
    public void selectAll() {
        List<User> users = userMapper.selectAll();
        for (User user : users) {
            System.out.println(user);
        }
    }
}
