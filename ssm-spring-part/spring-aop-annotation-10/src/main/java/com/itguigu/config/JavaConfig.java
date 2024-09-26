package com.itguigu.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(value = "com.itguigu")
@EnableAspectJAutoProxy // 开启aspect注解 等同于<aop:aspectj-autoproxy></aop:aspectj-autoproxy>
public class JavaConfig {

}
