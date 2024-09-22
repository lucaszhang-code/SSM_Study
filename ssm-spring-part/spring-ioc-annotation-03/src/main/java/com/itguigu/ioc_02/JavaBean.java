package com.itguigu.ioc_02;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

//@Scope(scopeName = ConfigurableBeanFactory.SCOPE_SINGLETON)
@Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Component
public class JavaBean {
    // 周期方法命名随意，但是必须是public void
    @PostConstruct
    public void init(){
        System.out.println("init");
    }

    @PreDestroy
    public void destroy(){
        System.out.println("destroy");
    }
}
