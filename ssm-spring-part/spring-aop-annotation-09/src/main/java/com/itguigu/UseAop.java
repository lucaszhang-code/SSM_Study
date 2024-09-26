package com.itguigu;

import com.itguigu.dyn.ProxyFactory;
import com.itguigu.statics.StaticProxyCalculator;

public class UseAop {
    public static void main(String[] args) {
        Calculator target = new CalculatorPureImpl();
        // 中介对象
        Calculator proxy = new StaticProxyCalculator(target);
        int result = proxy.add(1, 2);


        // jdk代理
        ProxyFactory factory = new ProxyFactory(target);
        // 使用接口借值 = 代理对象[兄弟 拜把子]
        Calculator proxy1 = (Calculator) factory.getProxy();
        proxy1.add(1, 2);
    }
}
