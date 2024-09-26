package com.itguigu.statics;

import com.itguigu.Calculator;

/**
 * 代理类
 */
public class StaticProxyCalculator implements Calculator {

    private Calculator calculator;

    // 使用构造方法传入目标
    public StaticProxyCalculator(Calculator target) {
        this.calculator = target;
    }

    @Override
    public int add(int i, int j) {
        // 非核心业务交给中介代理
        System.out.println("i = " + i + ", j = " + j);
        int result = calculator.add(i, j);
        System.out.println("result = " + result);
        return result;
    }

    @Override
    public int sub(int i, int j) {
        return 0;
    }

    @Override
    public int mul(int i, int j) {
        return 0;
    }

    @Override
    public int div(int i, int j) {
        return 0;
    }
}
