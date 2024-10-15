package com.atguigu.controller;

import com.atguigu.pojo.Schedule;
import com.atguigu.service.ScheduleService;
import com.atguigu.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("schedule")
@Slf4j
@CrossOrigin // 允许其他源访问
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    // 接收参数
    // 相应参数
    @GetMapping("/{pageSize}/{currentPage}")
    public R page(@PathVariable int pageSize, @PathVariable int currentPage) {

        R r = scheduleService.page(pageSize, currentPage);
        log.info("查询的数据位：{}", r);
        return r;
    }

    @DeleteMapping("/{id}")
    public R remove(@PathVariable int id) {
        R r = scheduleService.remove(id);
        return r;
    }

    @PostMapping
    public R save(@RequestBody @Validated Schedule schedule, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return R.fail(null);
        }
        R r = scheduleService.save(schedule);
        return r;
    }

    @PutMapping
    public R update(@RequestBody @Validated Schedule schedule, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return R.fail(null);
        }
        R r = scheduleService.update(schedule);
        return r;
    }

}
