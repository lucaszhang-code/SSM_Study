package com.itguigu.path;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("path")
@ResponseBody
public class PathController {
    // 接收动态参数必须使用PathVariable
    @RequestMapping("{account}/{password}")
    public String login(@PathVariable String account, @PathVariable String password) {
        System.out.println("account: " + account + " password: " + password);
        return "OK!";
    }
}
