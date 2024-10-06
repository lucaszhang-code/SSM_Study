package com.itguigu.test;

import com.itguigu.mappers.CustomerMapper;
import com.itguigu.mappers.OrderMapper;
import com.itguigu.pojo.Customer;
import com.itguigu.pojo.Order;
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

public class MybatisTest {

    private SqlSession sqlSession;

    @BeforeEach
    public void before() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(resourceAsStream);
        sqlSession = build.openSession();

    }

    @AfterEach
    public void clean() {
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void test() {
        OrderMapper orderMapper = sqlSession.getMapper(com.itguigu.mappers.OrderMapper.class);
        Order order = orderMapper.queryOrderById(1);
            System.out.println(order);
            System.out.println(order.getCustomerId());

    }

    @Test
    public void test1() {
        CustomerMapper customerMapper = sqlSession.getMapper(CustomerMapper.class);
        List<Customer> customers = customerMapper.queryList();
        for (Customer customer : customers) {
            System.out.println(customer);
        }
    }
}
