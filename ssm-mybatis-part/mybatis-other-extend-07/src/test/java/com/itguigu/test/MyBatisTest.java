package com.itguigu.test;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itguigu.mappers.EmployeeMapper;
import com.itguigu.pojo.Employee;
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
    // 使用分页插件
    public void test01() {
        EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
        // 调用之前先设置分页数据（当前第几页，每页显示多少条数据）
        PageHelper.startPage(1, 2);
        //TODO:不能将两条查询封装到一个分页区
        List<Employee> employeeList = employeeMapper.queryList();
        // 将查询的数据封装到一个pageInfo的分页实体类里面（一共有多少页，一共有多少条等等）
        PageInfo<Employee> pageInfo = new PageInfo<>(employeeList);
        // 当前页的数据，总条数，总页数
        int pages = pageInfo.getPages();
        System.out.println("pages: " + pages);
        // 总条数
        long total = pageInfo.getTotal();
        System.out.println("total: " + total);
        int pageSize = pageInfo.getPageSize();
        System.out.println("pageSize: " + pageSize);
        int pageNum = pageInfo.getPageNum();
        System.out.println("pageNum: " + pageNum);
    }
}
