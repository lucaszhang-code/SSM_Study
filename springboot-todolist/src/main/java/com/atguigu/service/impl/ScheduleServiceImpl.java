package com.atguigu.service.impl;

import com.atguigu.mappers.ScheduleMapper;
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
public class ScheduleServiceImpl implements ScheduleService {
    @Autowired
    private ScheduleMapper scheduleMapper;

    @Override
    public R page(int pageSize, int currentPage) {
        // 分页操作
        PageHelper.startPage(currentPage, pageSize);
        // 查询
        List<Schedule> scheduleList = scheduleMapper.queryByPage();
        // 分页数据装配
        PageInfo<Schedule> pageInfo = new PageInfo<>(scheduleList);
        // 装配
        PageBean<Schedule> pageBean = new PageBean<>(currentPage, pageSize, pageInfo.getTotal(), pageInfo.getList());

        return R.ok(pageBean);
    }

    @Override
    public R remove(int id) {
        int rows = scheduleMapper.deleteById(id);
        if (rows > 0) {
            return R.ok(null);
        }
        return R.fail(null);
    }

    @Override
    public R save(Schedule schedule) {
        int rows = scheduleMapper.insertData(schedule);
        if (rows > 0) {
            return R.ok(null);
        }
        return R.fail(null);
    }

    @Override
    public R update(Schedule schedule) {
        int rows = scheduleMapper.updateData(schedule);
        if (rows > 0) {
            return R.ok(null);
        }
        return R.fail(null);
    }
}
