package com.itguigu.ioc_03;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HappyComponent {

    //默认包含无参数构造函数

    public void doWork() {

        System.out.println("HappyComponent.doWork");
    }
}
