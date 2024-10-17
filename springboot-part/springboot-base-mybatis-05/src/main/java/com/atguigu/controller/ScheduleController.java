package com.atguigu.controller;

import com.atguigu.mapper.UserMapper;
import com.atguigu.pojo.Schedule;
import com.atguigu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("schedule")
@RestController
public class ScheduleController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserService userService;

    @GetMapping
    public List<Schedule> query() {
        return userMapper.queryList();
    }

//    @GetMapping
//    public void delete(){
//        userService.delete();
//    }

}
