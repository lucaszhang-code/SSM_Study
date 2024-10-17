package com.atguigu.mappers;

import com.atguigu.pojo.Schedule;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ScheduleMapper {
    List<Schedule> queryByPage();

    int deleteById(int id);

    int insertData(Schedule schedule);

    int updateData(Schedule schedule);
}
