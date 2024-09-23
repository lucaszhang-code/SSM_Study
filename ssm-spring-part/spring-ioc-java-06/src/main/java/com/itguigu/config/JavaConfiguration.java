package com.itguigu.config;

// Java的配置类，替代xml配置文件

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * 步骤一：添加@Configuration注解
 * 步骤二：实现上面的三个功能注解
 */
@ComponentScan(value = "com.itguigu.ioc_01")
@PropertySource(value = "classpath:jdbc.properties")
@Configuration
public class JavaConfiguration {
    @Value("${itguigu.url}")
    private String url;
    @Value("${itguigu.username}")
    private String username;
    @Value("${itguigu.password}")
    private String password;
    @Value("${itguigu.driver}")
    private String driverClassName;

    /**
     * 方法的返回类型 == bean组件的类型或者他的接口和父类
     * 方法的名字 = bean id
     * 方法可以定义具体实现过程
     * 最重要的一步：@Bean
     * @return dataSource
     *
     * beanName的问题
     */
    @Bean(value = "Lucas", initMethod = "", destroyMethod = "")
    public DruidDataSource dataSource1() {
        // 实现具体的实例化过程
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean(value = "Tom", initMethod = "", destroyMethod = "")
    public DruidDataSource dataSource2() {
        // 实现具体的实例化过程
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    // JdbcTemplate -> datasource
    @Bean
    public JdbcTemplate getJdbcTemplate() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        // 需要ioc里面的其他组件
        // 方案一：如果其他组件也是@Bean方法，可以直接调用 | 从ioc容器获取组件
        jdbcTemplate.setDataSource(dataSource1());
        return jdbcTemplate;
    }

    @Bean
    public JdbcTemplate getJdbcTemplate1(DataSource Lucas) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        // 需要ioc里面的其他组件
        // 方案二：形参列表声明的想要的组件类型，可以是一个，也可以是多个！ioc容器也会注入
        // 形参变量注入，要求必须有对应的类型和组件，如果没有抛异常
        // 如果有多个，可以写形参名称等同于对应bean标识即可
        jdbcTemplate.setDataSource(Lucas);
        return jdbcTemplate;
    }


}
