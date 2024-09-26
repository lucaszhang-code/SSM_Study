package test;

import com.itguigu.Calculator;
import com.itguigu.config.JavaConfig;
import com.itguigu.service.impl.CalculatorPureImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(value = JavaConfig.class)
public class SpringAopTest {
    // aop的底层会使用jdk代理
    @Autowired
    private Calculator calculator;

    @Test
    public void test() {
        int add = calculator.add(1, 0);
        System.out.println("add = " + add);
    }
}
