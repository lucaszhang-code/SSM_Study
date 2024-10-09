package com.itguigu.controller;


import com.itguigu.pojo.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("json")
@ResponseBody
public class JsonController {

    // data->post->{name,age,gender}
    // 前端415->不支持数据类型
    // java原生api只支持param参数和request.getParamer("key")
    // 1.导入json相关的处理依赖
    // 2.handlerAdapter配置JSON转化器
    @RequestMapping("data")
    public String data(@RequestBody Person person) {
        System.out.println("Person=" + person);
        return person.toString();
    }
}
