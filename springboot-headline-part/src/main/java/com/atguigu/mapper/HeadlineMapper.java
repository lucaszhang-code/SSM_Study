package com.atguigu.mapper;

import com.atguigu.pojo.Headline;
import com.atguigu.pojo.PortalVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;

import java.util.Map;

/**
* @author Lucas
* @description 针对表【news_headline】的数据库操作Mapper
* @createDate 2024-10-18 10:55:37
* @Entity com.atguigu.pojo.Headline
*/

@Mapper
public interface HeadlineMapper extends BaseMapper<Headline> {

    IPage<Map> selectMyPage(IPage<Map> page,@Param("portalVo") PortalVo portalVo);

    Map selectArticleById(int hid);
}




