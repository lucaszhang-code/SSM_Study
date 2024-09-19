package com.itguigu.test;


import com.itguigu.ioc_03.HappyComponent;
import com.itguigu.ioc_05.JavaBean;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringIocTest {
    //    如何创建ioc容器并且读取配置文件
    public void createIoc() {
        // 1.创建容器
        // 方式1：直接创建容器，并且指定配置文件
        // 构造函数（String...配置文件）可以填写一个或者多个
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-03.xml");

        // 先创建ioc对象，再指定配置文件，再刷新
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext();
        applicationContext.setConfigLocation("spring-03.xml");
        applicationContext.refresh();
    }


    // 讲解如何在ioc容器中获取组件bean

    public void getBeanFromIoc() {
        //1.创建ioc容器对象
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext();
        applicationContext.setConfigLocation("spring-03.xml");
        applicationContext.refresh();

        // 获取组件
        // 方案一，直接根据bean id获取,返回值类型是Object,需要强制转化类型，不推荐
//        HappyComponent happyComponent = (HappyComponent) applicationContext.getBean("happyComponent");

        // 方案二，根据beanId，同时指出bean的类型class
        HappyComponent happyComponent1 = applicationContext.getBean("happyComponent", HappyComponent.class);

        // 方案三，直接根据类型获取,同一个类型在ioc容器中只能有一个bean！！！
        // 如果ioc容器存在多个bean会出现异常
        HappyComponent happyComponent2 = applicationContext.getBean(HappyComponent.class);

        happyComponent1.doWork();
    }

    // 测试ioc配置的初始化和销毁方法
    @Test
    public void test_04(){

        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-04.xml");

        applicationContext.getBean(JavaBean.class);

        // 正常结束ioc容器
        applicationContext.close();
    }

    @Test
    public void test_05(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-05.xml");

       // 读取组件
        JavaBean javaBean = applicationContext.getBean("javaBean", JavaBean.class);
        System.out.println(javaBean);
    }
}
