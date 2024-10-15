package com.atguigu.service.impl;

import com.atguigu.mapper.ScheduleMapper;
import com.atguigu.pojo.Schedule;
import com.atguigu.service.ScheduleService;
import com.atguigu.utils.PageBean;
import com.atguigu.utils.R;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleImpl implements ScheduleService {

    @Autowired
    private ScheduleMapper scheduleMapper;

    @Override
    public R page(int pageSize, int currentPage) {
        // 分页操作
        PageHelper.startPage(currentPage, pageSize);
        // 查询
        List<Schedule> scheduleList = scheduleMapper.queryList();
        // 分页数据装配
        PageInfo<Schedule> pageInfo = new PageInfo<>(scheduleList);

        // 装配
        PageBean<Schedule> pageBean = new PageBean<>(currentPage, pageSize, pageInfo.getTotal(), pageInfo.getList());

        R r = R.ok(pageBean);
        return r;
    }

    @Override
    public R remove(int id) {
        int rows = scheduleMapper.deleteById(id);
        if(rows > 0){
            return R.ok(null);
        }
        return R.fail(null);
    }

    @Override
    public R save(Schedule schedule) {
        int rows = scheduleMapper.insert(schedule);
        if(rows > 0){
            return R.ok(null);
        }
        return R.fail(null);
    }

    @Override
    public R update(Schedule schedule) {
        if(schedule.getId() == null){
            return R.fail(null);
        }
        int rows = scheduleMapper.update(schedule);
        if(rows > 0){
            return R.ok(null);
        }
        return R.fail(null);
    }
}
