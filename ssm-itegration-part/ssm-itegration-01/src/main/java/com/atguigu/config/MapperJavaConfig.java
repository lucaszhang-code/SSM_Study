package com.atguigu.config;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import javax.sql.DataSource;

/**
 * TODO如果dataSource和myBatis组件配置在一起，会触发@Value注解不生效的情况
 * 原因 myBatis组件的加载顺序先加载
 */

// 配置连接池信息
@Configuration
public class MapperJavaConfig {
    // sqlSession加入ioc容器
    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSource) {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        // 指定数据库连接对象
        sqlSessionFactoryBean.setDataSource(dataSource);
        Resource resource = new ClassPathResource("myBatis-config.xml");
        sqlSessionFactoryBean.setConfigLocation(resource);
        return sqlSessionFactoryBean;
    }

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        // Mapper代理对象的factoryBean
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("com.atguigu.mapper");   // mapper接口和mapper.xml所在的共同包
        return mapperScannerConfigurer;
    }
}
