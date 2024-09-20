package com.itguigu.contoller;

import com.itguigu.pojo.Student;
import com.itguigu.service.StudentService;

import java.util.List;

public class StudentController {
    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }

    private StudentService studentService;

    public void findAll(){
        List<Student> all = studentService.findAll();
        System.out.println("最终学员数据为：" + all);
    }
}
