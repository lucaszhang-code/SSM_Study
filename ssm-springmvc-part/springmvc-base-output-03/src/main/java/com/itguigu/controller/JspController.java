package com.itguigu.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("jsp")
public class JspController {

    /**
     * TODO:快速查找视图
     * 1.方法返回值的类型是字符串
     * 2.不能添加@ResponseBody，直接返回字符串给浏览器
     * 3.返回值对应中间视图的名称
     *
     * @return
     */

    @GetMapping("index")
    public String index(HttpServletRequest request) {
        request.setAttribute("data", "Hello jsp");
        System.out.println("index");
        return "index";
    }

    @GetMapping("forward")
//    转发
    public String forward(){
        return "forward:/jsp/index";
    }


}
