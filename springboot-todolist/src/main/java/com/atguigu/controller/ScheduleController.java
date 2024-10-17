package com.atguigu.controller;

import com.atguigu.pojo.Schedule;
import com.atguigu.service.ScheduleService;
import com.atguigu.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("schedule")
@CrossOrigin
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @GetMapping("/{pageSize}/{currentPage}")
    public R getPageData(@PathVariable int pageSize, @PathVariable int currentPage) {
        return scheduleService.page(pageSize, currentPage);
    }

    @DeleteMapping("/{id}")
    public R delete(@PathVariable int id) {
        return scheduleService.remove(id);
    }

    @PostMapping
    public R save(@RequestBody @Validated Schedule schedule, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return R.fail(null);
        }

        System.out.println("schedule:" + schedule);
        return scheduleService.save(schedule);
    }

    @PutMapping
    public R update(@RequestBody @Validated Schedule schedule, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return R.fail(null);
        }
        return scheduleService.update(schedule);
    }
}
