package com.atguigu.test;

import com.atguigu.mapper.UserMapper;
import com.atguigu.pojo.User;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MyBatisQueryWrapper {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void test_01(){
        // 查询名字中包含A，且年龄在20-30之间,邮箱不等于null
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", "L");
        queryWrapper. between("age", 20, 30);
        queryWrapper.isNotNull("email");
        userMapper.selectList(queryWrapper);
    }

    @Test
    public void test_02(){
        // 按照年龄降序查询
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("age").orderByAsc("id");
        userMapper.selectList(queryWrapper);
    }

    @Test
    public void test_03(){
        // 删除邮箱为空的
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNotNull("email");
        userMapper.selectList(queryWrapper);
    }

    @Test
    public void test_04(){
        // 将年龄大于20且用户包含L或者邮箱为null的用户信息修改
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        // 条件之间默认and
        queryWrapper.gt("age", 20).
                like("name", "L").
                or().isNull("email");

        User user = new User();
        user.setAge(88);
        user.setEmail("hehehe.com");
        userMapper.update(user,queryWrapper);
    }

    @Test
    public void test_05(){
        // 查询user里面的name和age
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.gt("id", 1);
        queryWrapper.select("name", "age");
        userMapper.selectList(queryWrapper);
    }

    @Test
    public void test_06(){
        // 前端传入了两个参数，name和age，只有name不为空，作为条件；age>18，作为条件
        // 允许我们放一个表达式，如果为true都生效，反之不生效
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        String name = "Lucas";
        Integer age = 20;
        queryWrapper.eq((StringUtils.isNotBlank(name)),"name", name);
        queryWrapper.eq((age != null && age > 18), "age", age);
        userMapper.selectList(queryWrapper);
    }
}
