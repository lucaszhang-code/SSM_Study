package com.atguigu.pojo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 批量配置读取
 */


@Component
@Data
@ConfigurationProperties(prefix = "lucas.root") // 写通用的前缀
public class User {
    private String username;
    private String password;
    private List<String> gfs;
}
