package com.itguigu.test;

import com.itguigu.controller.StudentController;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringIocTest {
    @Test
    public void test() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-01.xml");
        StudentController studentController = context.getBean(StudentController.class);
        studentController.findAllStudents();

    }
}
