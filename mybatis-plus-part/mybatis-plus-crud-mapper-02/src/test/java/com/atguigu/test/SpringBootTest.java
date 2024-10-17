package com.atguigu.test;

import com.atguigu.mappers.UserMapper;
import com.atguigu.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@org.springframework.boot.test.context.SpringBootTest
public class SpringBootTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void insert() {
        User user = new User();
        user.setAge(18);
        user.setName("Lucas");
        user.setEmail("lucas@gmail.com");
        int insert = userMapper.insert(user);
        System.out.println(insert);
    }

    @Test
    public void delete(){
        int rows = userMapper.deleteById(1846537586920222721L);
        System.out.println(rows);
        // map删除
        Map param = new HashMap();
        param.put("age",20);
        int i = userMapper.deleteByMap(param);
        System.out.println(i);
    }

    @Test
    public void update(){
        User user = new User();
        user.setId(1L);
        user.setAge(30);
        int i = userMapper.updateById(user);
        System.out.println(i);

        // 将所有人的年龄改为22
        User user1 = new User();
        user1.setAge(22);
        int update = userMapper.update(user1, null);
        System.out.println(update);
    }

    @Test
    public void select(){
        User user = userMapper.selectById(1L);
        System.out.println(user);
        List<Long> ids = new ArrayList<>();
        ids.add(1L);
        ids.add(3L);
        List<User> users = userMapper.selectBatchIds(ids);
        for (User user1 : users) {
            System.out.println(user1);
        }
    }
}
