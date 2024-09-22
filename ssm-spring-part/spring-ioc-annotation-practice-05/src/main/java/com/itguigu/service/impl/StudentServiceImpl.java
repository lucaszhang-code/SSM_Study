package com.itguigu.service.impl;

import com.itguigu.dao.StudentDao;
import com.itguigu.pojo.Student;
import com.itguigu.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDao studentDao;

    @Override
    public List<Student> findAll() {
        List<Student> studentList = studentDao.queryAll();
        System.out.println("service: " + studentList);
        return studentList;
    }
}
