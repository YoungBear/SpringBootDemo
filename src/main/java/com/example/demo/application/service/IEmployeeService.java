package com.example.demo.application.service;

import com.example.demo.entity.EmployeeVo;

import java.util.List;

/**
 * @author youngbear
 * @email youngbear@aliyun.com
 * @date 2019-07-21 18:44
 * @blog https://blog.csdn.net/next_second
 * @github https://github.com/YoungBear
 * @description
 */
public interface IEmployeeService {

    /**
     * 添加一个 Employee
     *
     * @param employeeVo
     * @return 1-添加成功
     */
    Integer addEmployee(EmployeeVo employeeVo);

    /**
     * 根据 id 删除一个 Employee
     *
     * @param id
     * @return 1-删除成功
     */
    Integer deleteEmployee(Integer id);

    /**
     * 更新一个 Employee
     *
     * @param employeeVo
     * @return 更新成功后的结果
     */
    EmployeeVo updateEmployee(EmployeeVo employeeVo);

    /**
     * 根据 id 查询 Employee
     *
     * @param id
     * @return
     */
    EmployeeVo queryEmployee(Integer id);

    /**
     * 查询所有 Employee
     *
     * @return
     */
    List<EmployeeVo> selectAll();

}
