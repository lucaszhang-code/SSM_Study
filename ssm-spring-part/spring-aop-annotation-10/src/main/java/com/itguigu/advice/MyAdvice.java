package com.itguigu.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * 定义四个增强方法，获取目标方法
 * 定义方法：写增强代码
 * 2.使用注解来制定对应的位置
 * 3.配置切点表达式选中方法
 * 4.切面和ioc配置
 * 5.开启aspect注解和支持
 * 6.TODO:增强方法中获取目标方法和信息
 * 1.全部增强方法中，获取目标方法的信息（方法名，参数，访问修饰符，所属的类的信息……）
 * 2.返回的结果 @AfterReturning
 * 3.获取异常信息 @AfterThrowing
 */
@Component
@Aspect
public class MyAdvice {

    @Before("com.itguigu.pointcut.MyPointCut.myPc()")
    public void start(JoinPoint joinPoint) {
        // 1.获取方法所属的类的信息
        String simpleName = joinPoint.getTarget().getClass().getSimpleName();
        // 2.获取方法名
        String name = joinPoint.getSignature().getName();
        // 3.获取参数列表
        Object[] args = joinPoint.getArgs();
    }

    @AfterReturning(value = "com.itguigu.pointcut.MyPointCut.myPc()", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result){

    }

    @After("com.itguigu.pointcut.MyPointCut.myPc()")
    public void after(JoinPoint joinPoint){

    }

    @AfterThrowing(value = "com.itguigu.pointcut.MyPointCut.myPc()", throwing = "throwable")
    public void afterThrowing(JoinPoint joinPoint, Throwable throwable){

    }
}
