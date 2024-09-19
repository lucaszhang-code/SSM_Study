package com.itguigu.ioc_04;

public class JavaBean {
    // 必须是void和public以及无参数
    public void init() {
        System.out.println("JavaBean init");
    }

    // 销毁方法
    public void destroy() {
        System.out.println("JavaBean destroy");
    }
}
