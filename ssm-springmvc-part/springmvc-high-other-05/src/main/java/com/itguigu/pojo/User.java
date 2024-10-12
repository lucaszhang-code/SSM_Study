package com.itguigu.pojo;



import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Past;
import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import java.util.Date;

/**
 * name不为空
 *
 * @NotBlank 集合，包装类型
 * password长度大于6
 * age必须大于1
 * email符合格式
 * birthday是过去时间
 */

@Data
public class User {
    @NotBlank
    private String name;
    @Length(min = 6)
    private String password;
    @Min(1)
    private int age;
    @Email
    private String email;
    @Past
    private Date birthday;
}
