package com.itguigu.mapper;

import com.itguigu.pojo.Employee;

public interface EmployeeMapper {

    Employee queryById(Integer id);

    int deleteById(Integer id);

    /**
     * 根据员工id查询员工数据方法
     * @param empId  员工id
     * @return 员工实体对象
     */
    Employee selectEmployee(Integer empId);

}
