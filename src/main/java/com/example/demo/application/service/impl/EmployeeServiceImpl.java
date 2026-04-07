package com.example.demo.application.service.impl;

import com.example.demo.application.service.IEmployeeService;
import com.example.demo.domain.entity.EmployeeVo;
import com.example.demo.domain.repository.dao.IEmployeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public Integer addEmployee(EmployeeVo employeeVo) {
        return employeeDao.add(employeeVo);
    }

    @Override
    @Transactional
    public Integer deleteEmployee(Integer id) {
        return employeeDao.delete(id);
    }

    @Override
    @Transactional
    public EmployeeVo updateEmployee(EmployeeVo employeeVo) {
        employeeDao.update(employeeVo);
        return employeeDao.findEmployeeById(employeeVo.getId());
    }

    @Override
    public EmployeeVo queryEmployee(Integer id) {
        return employeeDao.findEmployeeById(id);
    }

    @Override
    public List<EmployeeVo> selectAll() {
        return employeeDao.selectAll();
    }

    @Override
    public List<EmployeeVo> selectByPage(int offset, int limit) {
        return employeeDao.selectByPage(offset, limit);
    }
}
