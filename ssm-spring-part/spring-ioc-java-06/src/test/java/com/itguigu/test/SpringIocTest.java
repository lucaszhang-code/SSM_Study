package com.itguigu.test;

import com.itguigu.config.JavaConfiguration;
import com.itguigu.config.JavaConfigurationA;
import com.itguigu.config.JavaConfigurationB;
import com.itguigu.ioc_01.StudentController;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class SpringIocTest {
    @Test
    public void test() {
        // 1.创建ioc容器
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(JavaConfiguration.class);

        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
        annotationConfigApplicationContext.register(JavaConfiguration.class);
        annotationConfigApplicationContext.refresh();
        // 2.获取配置类
        StudentController bean = applicationContext.getBean(StudentController.class);

        System.out.println("bean = " + bean);
    }

    @Test
    public void test2() {
        ApplicationContext applicationContext1 = new AnnotationConfigApplicationContext(JavaConfigurationA.class);
    }
}
