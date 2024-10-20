package com.atguigu.controller;


import com.atguigu.pojo.Headline;
import com.atguigu.service.HeadlineService;
import com.atguigu.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("headline")
@CrossOrigin
public class HeadlineController {
    @Autowired
    private HeadlineService headlineService;

    @PostMapping("publish")
    public Result publish(@RequestHeader String token, @RequestBody Headline headline) {
        return headlineService.publish(headline, token);
    }

    @PostMapping("findHeadlineByHid")
    public Result findHeadlineByHid(@RequestHeader String token,int hid) {
        return headlineService.findHeadlineByHid(token, hid);
    }

    @PostMapping("update")
    public Result update(@RequestHeader String token, @RequestBody Headline headline) {
        return headlineService.updateArticle(token, headline);
    }

    @PostMapping("removeByHid")
    public Result removeById(Integer hid){
        headlineService.removeById(hid);
        return Result.ok(null);
    }

}
