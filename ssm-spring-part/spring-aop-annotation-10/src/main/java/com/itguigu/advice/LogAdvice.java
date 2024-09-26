package com.itguigu.advice;

import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * 1.增强类的内部要存储增强代码
 * 2.注解配置，指定插入目标的位置
 * 前置 @Before
 * 后置 @AfterReturning
 * 环绕 @AfterThrowing
 * 环绕 @Around
 * 3.配置切点表达式
 * 4.补全注解
 * 加入ioc容器
 * 配置切面
 * 5.开启aspect注解
 */

@Component
@Aspect
public class LogAdvice {

    /**
     * TODO:切点表达式
     * 固定语法 execution（切点表达式）
     * 1.访问修饰符
     * public / private
     * 2.方法的返回参数类型
     * String int void
     * 如果不考虑访问修饰符和返回值 这两位整合成一起写*
     * 3.包的位置
     */

    /**
     * TODO:
     * 1.查询某包某类下，访问修饰符是公有，返回值是int的全部方法
     * public int xx.xx.jj.*(..)
     * 2.查询某包下类中第一个参数是String的方法
     *  * xx.xx.jj.*(String..)
     * 3.查询全部包下，无参数的方法！
     *  *.*..*.*()
     * 4.查询com包下，以int参数类型结尾的方法
     *  * com..*.*(..int)
     * 5.查询指定包下，Service开头类的私有返回值int的无参数方法
     *  private int xx.xx.Service*.*()
     */

    /**
     * 切点表达式的提取和复用
     * 1.当前类中提取
     * 定义一个空方法
     * 注解@Pointcut()
     * 2.创建一个存储切点的类，单独维护切点表达式
     **/



    @Before("com.itguigu.pointcut.MyPointCut.pc()")
    public void start() {
        System.out.println("方法开始了");
    }

    @After("com.itguigu.pointcut.MyPointCut.pc()")
    public void end() {
        System.out.println("方法结束了");
    }

    @AfterThrowing("com.itguigu.pointcut.MyPointCut.pc()")
    public void error() {
        System.out.println("方法报错了");
    }
}
