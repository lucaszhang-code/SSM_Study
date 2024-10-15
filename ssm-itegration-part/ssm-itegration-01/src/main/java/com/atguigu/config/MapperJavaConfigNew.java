package com.atguigu.config;

import org.apache.ibatis.logging.slf4j.Slf4jImpl;
import org.apache.ibatis.session.AutoMappingBehavior;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * 不保留配置文件
 * TODO如果dataSource和myBatis组件配置在一起，会触发@Value注解不生效的情况
 * 原因 myBatis组件的加载顺序先加载
 */

// 配置连接池信息
@Configuration
public class MapperJavaConfigNew {
    // sqlSession加入ioc容器
    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSource) {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        // 指定数据库连接对象
        sqlSessionFactoryBean.setDataSource(dataSource);
        // 指定myBatis配置文件的功能


//        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
//        configuration.setMapUnderscoreToCamelCase(true);
//        configuration.setLogImpl(Slf4jImpl.class);
//        configuration.setAutoMappingBehavior(AutoMappingBehavior.FULL);
//        sqlSessionFactoryBean.setConfiguration(configuration);
//        sqlSessionFactoryBean.setTypeAliasesPackage("com.atguigu.pojo");    // 别名的设置
//        PageInterceptor pageInterceptor = new PageInterceptor();
//        Properties properties = new Properties();
//        properties.setProperty("helperDialect", "mysql");
//        pageInterceptor.setProperties(properties);
//        sqlSessionFactoryBean.addPlugins(pageInterceptor);


        Resource resource = new ClassPathResource("mybatis-config.xml");
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
