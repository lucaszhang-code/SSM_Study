package com.itguigu.controller;

import com.itguigu.pojo.Student;
import com.itguigu.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class StudentController {
    @Autowired
    private StudentService studentService;

    public void findAllStudents() {
        List<Student> studentList = studentService.findAll();
        System.out.println("controller: " + studentList);
    }
}
