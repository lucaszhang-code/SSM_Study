package com.atguigu.mapper;
import com.atguigu.pojo.Schedule;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
   List<Schedule> queryList();

   int delete(int i);
}
