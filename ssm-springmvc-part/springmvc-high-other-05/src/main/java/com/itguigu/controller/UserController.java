package com.itguigu.controller;

import com.itguigu.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("user")
public class UserController {

    @GetMapping("data")
    public String data() {
        String name = null;
//        name.toString();
        return "OK";
    }

    @RequestMapping("data1")
    public String data1() {
//        int i = 1 / 0;
        return "OK";
    }

    /**
     * 实体类添加校验注解
     * handler(@Validated实体类对象)
     * 接受错误绑定信息，自定义返回结果
     * 自己捕捉错误信息
     */
    @PostMapping("register")
    public Object register(@Validated @RequestBody User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // 有绑定错误，就不直接返回，由我们自己决定
            Map data = new HashMap();
            data.put("code", 400);
            data.put("msg", "参数校验异常");
            return data;
        }
        System.out.println("user:" + user);
        return user;
    }
}
