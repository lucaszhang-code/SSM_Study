package com.itguigu.advice;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TxAround {

    /**
     * 环绕通知需要通知中目标方法的执行
     * @return
     */
    @Around("com.itguigu.pointcut.MyPointCut.pc()")
    public Object transaction(ProceedingJoinPoint joinPoint) throws Throwable {

        // 保证目标方法
        Object[] args = joinPoint.getArgs();
        Object result = null;
        try {
            // 增强代码
            System.out.println("开启事务");
            result = joinPoint.proceed(args);
            System.out.println("结束事务");
        } catch (Throwable e) {
            System.out.println("事务回滚");
            throw new Throwable();
        } finally {

        }

        return result;
    }

}
