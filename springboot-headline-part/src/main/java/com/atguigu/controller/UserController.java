package com.atguigu.controller;

import com.atguigu.pojo.User;
import com.atguigu.service.UserService;
import com.atguigu.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
@CrossOrigin
public class UserController {
    @Autowired
    private  UserService userService;

    @PostMapping("login")
    public Result login(@RequestBody User user) {
        return userService.login(user);
    }

    @GetMapping("getUserInfo")
    public Result getInfo(@RequestHeader String token) {
        return userService.getUserInfo(token);
    }
}
