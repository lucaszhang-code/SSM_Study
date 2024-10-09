package com.itguigu.requestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("user") // 类上添加RequestMapping,方法就不用加了
public class UserController {

    @GetMapping // 相当于@RequestMapping(value = "login", method = RequestMethod.GET)
    public String index(){
        return "index";
    }

    // 注册作用地址，将handler注册到handlerMapping
    // RequestMapping不要求必须使用/开头

    /**
     * 精准地址[一个或者多个] /user/login (地址一，地址二)
     * 支持模糊地址* (任意一层字符串 | ** 任意层任意字符串)
     *
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login() {
        return null;
    }

    @RequestMapping(value = "register", method = {RequestMethod.POST, RequestMethod.GET})
    public String register() {
        return null;
    }
    // 报405，前端请求方式错了
}
