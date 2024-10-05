package com.itguigu.mapper;

import com.itguigu.pojo.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface EmployeeMapper {

    // 根据id查询员工对象
    Employee queryById(Integer id);

    // 根据id删除员工
    int deleteById(Integer id);

    // 根据工资查询
    List<Employee> queryBySalary(Double salary);

    // 插入员工数据
    int insertEmp(Employee employee);

    // 根据员工姓名和工资查询
    // @Param指定value值
    Employee queryByNameAndSalary(@Param("a") String name,@Param("b") Double salary);

    // 传入map数据map(name=员工名字, salary=工资)
    // mapper接口中不允许重载
    int insertEmpMap(Map data);
}
