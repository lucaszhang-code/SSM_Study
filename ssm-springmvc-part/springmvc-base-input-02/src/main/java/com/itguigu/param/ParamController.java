package com.itguigu.param;

import com.itguigu.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("param")
@ResponseBody
public class ParamController {
    // 直接接收
    // 1.要求名称相同 2.可以不传参 不报错
    @GetMapping(value="/value")
    public String setupForm(String name,int age){
        System.out.println("name = " + name + ", age = " + age);
        return name + age;
    }

    // 注解指定
    @RequestMapping("/data1")
    public String data1(@RequestParam(value = "account") String account,
                        @RequestParam(required = false, defaultValue = "1") int page) {
        System.out.println("username:" + account + " page:" + page);
        return account + page;
    }

    @RequestMapping("/data2")
    public String data2(@RequestParam List<String> hbs) {
        System.out.println("hbs:" + hbs);
        return "OK";
    }

    @RequestMapping("/data3")
    public String data3(User user) {
        System.out.println("user:" + user);
        return user.toString();
    }

}


