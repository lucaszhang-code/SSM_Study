package com.itguigu.mappers;

import com.itguigu.pojo.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeMapper {
    List<Employee> query(@Param("name") String name, @Param("salary") Double salary);

    // 根据id更新员工的数据,只有name和Salary不为空的时候更新
    int update(Employee employee);

    List<Employee> queryBatch(@Param("ids") List<Integer> ids);

    List<Employee> deleteBatch(@Param("ids") List<Integer> ids);

    int insertBatch(@Param("list") List<Employee> employeeList);

    int updateBatch(@Param("list") List<Employee> employeeList);


}
