package com.atguigu.service;

import com.atguigu.pojo.Headline;
import com.atguigu.pojo.PortalVo;
import com.atguigu.utils.Result;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author Lucas
* @description 针对表【news_headline】的数据库操作Service
* @createDate 2024-10-18 10:55:37
*/
public interface HeadlineService extends IService<Headline> {

    Result findNewsPage(PortalVo portalVo);

    Result showHeadlineDetail(int hid);

    Result publish(Headline headline, String token);

    Result findHeadlineByHid(String token, int hid);

    Result updateArticle(String token, Headline headline);

}
