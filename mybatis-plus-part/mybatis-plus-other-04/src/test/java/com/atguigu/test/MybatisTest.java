package com.atguigu.test;

import com.atguigu.mapper.UserMapper;
import com.atguigu.pojo.User;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MybatisTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testPage() {
        // IPage->page
        Page<User> page = new Page<>(1, 3);
        userMapper.selectPage(page, null);
        // 结果也会被封装
        System.out.println(page.getCurrent());  //页码
        System.out.println(page.getSize()); // 页容量
        System.out.println(page.getRecords());  // 当前页的数据
        System.out.println(page.getTotal());    // 总条数
    }

    @Test
    public void testMyPage() {
        Page<User> page = new Page<>(1, 3);
        userMapper.queryByAge(page, 21);
        System.out.println(page.getCurrent());  //页码
        System.out.println(page.getSize()); // 页容量
        System.out.println(page.getRecords());  // 当前页的数据
        System.out.println(page.getTotal());    // 总条数
    }
}
