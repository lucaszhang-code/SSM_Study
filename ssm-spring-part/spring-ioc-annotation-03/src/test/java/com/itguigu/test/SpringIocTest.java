package com.itguigu.test;

import com.itguigu.ioc_01.XxxDao;
import com.itguigu.ioc_01.XxxService;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringIocTest {
    @Test
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
}
