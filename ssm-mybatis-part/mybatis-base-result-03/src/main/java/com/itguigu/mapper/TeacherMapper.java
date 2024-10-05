package com.itguigu.mapper;

import com.itguigu.pojo.Teacher;

public interface TeacherMapper {

    int insertTeacher(Teacher teacher);

    Teacher queryById(Integer id);
}
