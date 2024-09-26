package com.itguigu.advice;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Order(10)  // 指定优先级的值，值越小，优先级越高
public class TxAdvice {

    @Before("com.itguigu.pointcut.MyPointCut.pc()")
    public void begin(){
        System.out.println("begin");
    }

    @AfterReturning("com.itguigu.pointcut.MyPointCut.pc()")
    public void commit(){
        System.out.println("commit");
    }

    @AfterThrowing("com.itguigu.pointcut.MyPointCut.pc()")
    public void rollback(){
        System.out.println("rollback");
    }
}
