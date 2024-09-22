package com.itguigu.ioc_03;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;


@Controller
public class UserController {

//    @Autowired(required = false)
    // <property userService -> 对应类型的bean装配
    // 自动装配注解DI：1.ioc容器中查找符合类型的组件对象 2.给当前属性设置DI
    // 佛系装配，可以没有装配，值为空 不推荐使用
//    private UserService userService;

//    @Resource(name = "userServiceImpl")
    @Autowired
    @Qualifier("newUserServiceImpl")
    private UserService userService;


    public void show(){
        // 调用业务层的方法
        String show = userService.show();
        System.out.println(show);
    }

}
