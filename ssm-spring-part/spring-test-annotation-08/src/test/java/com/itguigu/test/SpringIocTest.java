package com.itguigu.test;

import com.itguigu.components.A;
import com.itguigu.components.B;
import com.itguigu.config.JavaConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;


//@SpringJUnitConfig(locations = // 指定配置文件的)
@SpringJUnitConfig(value = JavaConfig.class)
public class SpringIocTest {

    @Autowired
    private A a;
    @Autowired
    private B b;

    @Test
    public void test() {

    }
}
