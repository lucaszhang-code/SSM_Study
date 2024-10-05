package com.itguigu.mapper;

import com.itguigu.pojo.Employee;

import java.util.List;
import java.util.Map;

public interface EmployeeMapper {
    // 如果我们是dml语句 返回值是int
    int deleteById(Integer id);

    // 指定输出类型
    // 指定单个输出类型
    String queryNameById(Integer id);

    // 根据员工id查询工资
    Double querySalaryById(Integer id);

    // 返回单个自定义实体类型
    Employee queryEmployeeById(Integer id);

    // 查询部门的最高工资和平均工资
    Map<String,Object> selectEmpNameAndMaxSalary();

    // 查询工资高于某个数的员工
    List<Employee> selectAll();

    // 员工插入
    int insertEmp(Employee employee);
}
