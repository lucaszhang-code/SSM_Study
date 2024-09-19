package com.itguigu;


import com.alibaba.druid.pool.DruidDataSource;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

public class jdbcTemplateTest {
//    public void testForJava(){
//
//        /**
//         * jdbcTemplate只是简化数据库的crud动作 不提供连接池
//         *
//         * 配合DruidDataSource 负责连接的数据创建注册等等
//         */
//
//        // 0.创建一个连接池对象
//        DruidDataSource dataSource = new DruidDataSource();
//        dataSource.setUrl("jdbc:mysql:///studb");
//        dataSource.setUsername("root");
//        dataSource.setPassword("123456");
//        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
//
//        // 1，实例化对象
//        JdbcTemplate jdbcTemplateTest = new JdbcTemplate();
//
//        jdbcTemplateTest.setDataSource(dataSource);
//
//        // 2.调用方法
//        // jdbcTemplate.update() DDL DML DCL 非查询语句
//        // jdbcTemplateTest.queryForObject(); DQL查询单个对象
//        // jdbcTemplate.query() DQL集合
//
//
//    }

    @Test
    public void testForIoc(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-01.xml");
        JdbcTemplate bean = applicationContext.getBean(JdbcTemplate.class);
        // 插入
        String sql = "insert into students(id,name,gender,age,class) values(?,?,?,?,?)";
        int rows = bean.update(sql, 9,"Lucas", "男", "20", "大三");
        System.out.println(rows);
    }
}
