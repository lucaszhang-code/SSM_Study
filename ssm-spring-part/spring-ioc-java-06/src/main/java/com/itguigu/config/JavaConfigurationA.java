package com.itguigu.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(value = JavaConfigurationB.class)
public class JavaConfigurationA {

}
