package com.itguigu.service;

import com.itguigu.dao.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileInputStream;

@Transactional(timeout = 3)
@Service
public class StudentService {

    @Autowired
    private StudentDao studentDao;

    /**
     * 添加事务
     *
     * @Transactional 只读模式可以提升事务的效率
     *
     * 超时时间 默认永不超时
     * 设置timeout = 时间，如果超过设置时间就抛出异常
     *
     */

//    @Transactional(readOnly = false, timeout = 3)
//    public void changeInfo()  {
//        studentDao.updateAgeById(88, 1);
//        try {
//            Thread.sleep(4);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        System.out.println("-----------");
//        studentDao.updateNameById("test2", 2);
//    }

    @Transactional(readOnly = false, timeout = 3)
    public void changeInfo()  {
        studentDao.updateAgeById(100, 1);
        studentDao.updateNameById("test2", 2);
    }

    @Transactional(readOnly = true)
    public void getStudentInfo(){

    }
}
