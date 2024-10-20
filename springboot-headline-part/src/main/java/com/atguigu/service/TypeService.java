package com.atguigu.service;

import com.atguigu.pojo.PortalVo;
import com.atguigu.pojo.Type;
import com.atguigu.utils.Result;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author Lucas
* @description 针对表【news_type】的数据库操作Service
* @createDate 2024-10-18 10:55:37
*/
public interface TypeService extends IService<Type> {

    List<Type> findAllTypes();


}
