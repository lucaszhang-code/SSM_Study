package com.itguigu.pojo;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

@Data
public class User {
    @Value("Lucas")
    private String name;
    private int age;
}
