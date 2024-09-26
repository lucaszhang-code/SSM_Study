package com.itguigu.pointcut;

import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
public class MyPointCut {

    @Pointcut("execution(* com.itguigu.service.impl.*.*(..))")
    public void pc() {

    }

    @Pointcut("execution(* com..impl.*.*(..))")
    public void myPc(){

    }

}
