package com.atguigu.service;

import com.atguigu.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    @Transactional
    public void delete() {
        int rows = userMapper.delete(2);
        System.out.println("rows:" + rows);
        int i = 1 / 0;
    }
}
