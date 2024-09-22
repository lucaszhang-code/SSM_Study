package com.itguigu.test;

import com.itguigu.ioc_01.XxxDao;
import com.itguigu.ioc_01.XxxService;
import com.itguigu.ioc_02.JavaBean;
import com.itguigu.ioc_03.UserController;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class

SpringIocTest {
//    @Test
    public void test01(){
        // 1.创建ioc容器
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-01.xml");
        // 2.获取组件
        XxxDao bean = context.getBean(XxxDao.class);
        System.out.println("bean = " + bean);
        XxxService xxxService = context.getBean("xxxService", XxxService.class);
        System.out.println("xxxService = " + xxxService);

        // 3.close关闭
        context.close();
    }

//    @Test
    public void test02(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-02.xml");
        JavaBean bean = context.getBean(JavaBean.class);
        JavaBean bean1 = context.getBean(JavaBean.class);
        System.out.println("bean = " + bean);
        System.out.println("bean1 = " + bean1);
    }

    @Test
    public void test03(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-03.xml");
        UserController userController = context.getBean(UserController.class);
        // 1,ioc容器中有一个UserService接口对应的实现类对象
        userController.show();

        // 2.如果ioc容器没有对应类型如何处理
        // @AutoWired进行装配，至少要求有一个bean，不然会报错

        // 3.有同一个类型有多个对应的组件，@AutoWired或报错
        // 使用@Qualifier(value="")使用该类指定id，必须搭配@AutoWired使用
        // 优化点 @Resource(name="")
    }

    @Test
    public void test04(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-04.xml");
        com.itguigu.ioc_04.JavaBean javaBean = context.getBean(com.itguigu.ioc_04.JavaBean.class);
        System.out.println(javaBean);
    }
}
