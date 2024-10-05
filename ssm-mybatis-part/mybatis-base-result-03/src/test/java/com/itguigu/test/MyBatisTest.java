package com.itguigu.test;

import com.itguigu.mapper.EmployeeMapper;
import com.itguigu.mapper.TeacherMapper;
import com.itguigu.pojo.Employee;
import com.itguigu.pojo.Teacher;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class MyBatisTest {

    InputStream resourceAsStream = Resources.getResourceAsStream("mybatis-config.xml");

    SqlSessionFactory session = new SqlSessionFactoryBuilder().build(resourceAsStream);

    SqlSession sqlSession = session.openSession();



    public MyBatisTest() throws IOException {
    }

    @Test
    public void testQueryEmpNameAndSalary() throws IOException {

        EmployeeMapper employeeMapper = sqlSession.getMapper(com.itguigu.mapper.EmployeeMapper.class);

        Map<String, Object> resultMap = employeeMapper.selectEmpNameAndMaxSalary();

        Set<Map.Entry<String, Object>> entrySet = resultMap.entrySet();

        for (Map.Entry<String, Object> entry : entrySet) {

            String key = entry.getKey();

            Object value = entry.getValue();

            System.out.println("key" + key + "value" + value);

        }

    }
    @Test
    public void testInsertEmp(){
        EmployeeMapper employeeMapper = sqlSession.getMapper(com.itguigu.mapper.EmployeeMapper.class);

        Employee employee = new Employee();
        employee.setEmpName("Lucas");
        employee.setEmpSalary(2000.0);

        int rows = employeeMapper.insertEmp(employee);
        System.out.println(rows);

        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testInsertTeacher(){
        TeacherMapper teacherMapper = sqlSession.getMapper(com.itguigu.mapper.TeacherMapper.class);

        Teacher teacher = new Teacher();
//        String id = UUID.randomUUID().toString().replaceAll("-", "");
//        teacher.settId(id);
        teacher.settName("朱朱");
        int i = teacherMapper.insertTeacher(teacher);
        System.out.println("获取id值: " + teacher.gettId());
        System.out.println(i);
        sqlSession.commit();
        sqlSession.close();
    }
}
