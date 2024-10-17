package com.atguigu.test;

import com.atguigu.pojo.User;
import com.atguigu.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class MybatisTest {

    @Autowired
    private UserService userService;

    @Test
    public void save() {
        List<User> users = new ArrayList<>();
        User user = new User();
        user.setName("Lucas");
        user.setAge(21);
        user.setEmail("lucas@gmail.com");
        users.add(user);

        User user1 = new User();
        user1.setName("Lucas");
        user1.setAge(21);
        user1.setEmail("lucas@gmail.com");
        users.add(user1);

        boolean b = userService.saveBatch(users);
        System.out.println(b);
    }

    @Test
    public void saveOrUpdate() {
        User user = new User();
        user.setId(1L);
        user.setName("Lucas");
        user.setAge(21);
        user.setEmail("lucas@gmail.com");
        userService.saveOrUpdate(user);
    }

    @Test
    public void update() {
        User user = new User();
        user.setId(1L);
        user.setName("Andy");
        user.setAge(21);
        user.setEmail("lucas@gmail.com");
        userService.updateById(user);
    }

    @Test
    public void delete(){

    }
}
