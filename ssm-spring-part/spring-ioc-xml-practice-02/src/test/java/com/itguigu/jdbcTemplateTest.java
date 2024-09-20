package com.itguigu;


import com.alibaba.druid.pool.DruidDataSource;
import com.itguigu.contoller.StudentController;
import com.itguigu.pojo.Student;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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
    public void testForIoc() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-01.xml");
        JdbcTemplate bean = applicationContext.getBean(JdbcTemplate.class);
        // 插入
        String sql = "insert into students(id,name,gender,age,class) values(?,?,?,?,?)";
        int rows = bean.update(sql, 10, "Tom", "男", "20", "大三");
        System.out.println(rows);
        sql = "select * from students where id = ?";
        Student student1 = bean.queryForObject(sql, new RowMapper<Student>() {
            @Override
            public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
                // rs结果集，rowNUm是行数
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setGender(rs.getString("gender"));
                student.setAge(rs.getInt("age"));
                student.setClasses(rs.getString("class"));
                return student;
            }
        }, 9);
        System.out.println("student1 =" + student1);

        sql = "select id, name, gender, age, class as classes from students";
        // TODO:BeanPropertyRowMapper帮助我们自动映射列和属性值
        List<Student> studentList = bean.query(sql, new BeanPropertyRowMapper<Student>(Student.class));
        System.out.println("studentList = " + studentList);
    }

    @Test
    public void testQueryAll() {
//        1.创建ioc容器
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-02.xml");
//        2.获取组件对象
        StudentController controller = applicationContext.getBean(StudentController.class);
//        3.使用组件对象
        controller.findAll();
//        4.关闭容器
        applicationContext.close();
    }

}
