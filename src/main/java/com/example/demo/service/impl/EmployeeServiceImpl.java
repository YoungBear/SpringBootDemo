package com.example.demo.service.impl;

import com.example.demo.dao.IEmployeeDao;
import com.example.demo.entity.EmployeeEntity;
import com.example.demo.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author youngbear
 * @email youngbear@aliyun.com
 * @date 2019-07-21 18:44
 * @blog https://blog.csdn.net/next_second
 * @github https://github.com/YoungBear
 * @description
 */
@Service
public class EmployeeServiceImpl implements IEmployeeService {

    @Autowired
    private IEmployeeDao employeeDao;

    @Override
    public Integer addEmployee(EmployeeEntity employeeEntity) {
        return employeeDao.add(employeeEntity);
    }

    @Override
    public Integer deleteEmployee(Integer id) {
        return employeeDao.delete(id);
    }

    @Override
    public EmployeeEntity updateEmployee(EmployeeEntity employeeEntity) {
        employeeDao.update(employeeEntity);
        return employeeDao.findEmployeeById(employeeEntity.getId());
    }

    @Override
    public EmployeeEntity queryEmployee(Integer id) {
        return employeeDao.findEmployeeById(id);
    }

    @Override
    public List<EmployeeEntity> selectAll() {
        return employeeDao.selectAll();
    }
}
