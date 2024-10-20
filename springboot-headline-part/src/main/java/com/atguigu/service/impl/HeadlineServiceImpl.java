package com.atguigu.service.impl;

import com.atguigu.mapper.UserMapper;
import com.atguigu.pojo.PortalVo;
import com.atguigu.utils.JwtHelper;
import com.atguigu.utils.Result;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.pojo.Headline;
import com.atguigu.service.HeadlineService;
import com.atguigu.mapper.HeadlineMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Lucas
 * @description 针对表【news_headline】的数据库操作Service实现
 * @createDate 2024-10-18 10:55:37
 */

@Service
public class HeadlineServiceImpl extends ServiceImpl<HeadlineMapper, Headline>
        implements HeadlineService {

    @Autowired
    private HeadlineMapper headlineMapper;
    @Autowired
    private JwtHelper jwtHelper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public Result findNewsPage(PortalVo portalVo) {

        IPage<Map> page = new Page<>(portalVo.getPageNum(), portalVo.getPageSize());
        headlineMapper.selectMyPage(page, portalVo);
        List<Map> records = page.getRecords();
        Map<String, Object> map = new HashMap<>();
        map.put("pageData", records);
        map.put("pageNum", portalVo.getPageNum());
        map.put("pageSize", portalVo.getPageSize());
        map.put("totalPage", page.getPages());
        map.put("totalSize", page.getTotal());

        Map<String, Object> info = new HashMap<>();
        info.put("pageInfo", map);

        return Result.ok(info);
    }

    @Override
    public Result showHeadlineDetail(int hid) {
        Map headLineDetail = headlineMapper.selectArticleById(hid);
        Headline headline = new Headline();
        headline.setHid(hid);
        headline.setPageViews((Integer) headLineDetail.get("pageViews") + 1); //阅读量+1
        headline.setVersion((Integer) headLineDetail.get("version")); //设置版本
        headlineMapper.updateById(headline);
        Map<String, Object> info = new HashMap<>();
        info.put("headline", headLineDetail);
        return Result.ok(info);
    }

    @Override
    public Result publish(Headline headline, String token) {
        int userId = jwtHelper.getUserId(token).intValue();
        headline.setPublisher(userId);
        headline.setPageViews(0);
        headline.setCreateTime(new Date());
        headline.setUpdateTime(new Date());
        headlineMapper.insert(headline);
        return Result.ok(null);
    }

    @Override
    public Result findHeadlineByHid(String token, int hid) {
        Headline headline = headlineMapper.selectById(hid);
        Map<String, Object> map = new HashMap<>();
        map.put("hid", headline.getHid());
        map.put("title", headline.getTitle());
        map.put("article", headline.getArticle());
        map.put("type", headline.getType());
        Map<String, Object> info = new HashMap<>();
        info.put("headline", map);
        return Result.ok(info);
    }

    @Override
    public Result updateArticle(String token, Headline headline) {
        //读取版本
        Integer version = headlineMapper.selectById(headline.getHid()).getVersion();

        headline.setVersion(version);
        headline.setUpdateTime(new Date());

        headlineMapper.updateById(headline);

        return Result.ok(null);
    }

}




