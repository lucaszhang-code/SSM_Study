package com.itguigu.json;

import com.itguigu.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("json")
@RestController // RestController = Controller + ResponseBody
public class JsonController {

    /**
     * TODO:@ResponseBody 直接放入响应体返回,不会走视图解析器
     * @return
     */

    @GetMapping("data")
    public User data() {
        User user = new User();
        user.setName("Lucas");
        user.setAge(20);
        return user;    // 转成json字符串
    }

    @GetMapping("data1")
    public List<User> dataList() {
        User user = new User();
        user.setName("Lucas");
        user.setAge(20);
        List<User> list = new ArrayList<>();
        list.add(user);
        return list;
    }
}
