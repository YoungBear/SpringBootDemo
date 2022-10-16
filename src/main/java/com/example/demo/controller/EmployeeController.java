package com.example.demo.controller;

import com.example.demo.entity.EmployeeVo;
import com.example.demo.entity.common.ResultVo;
import com.example.demo.exception.DemoException;
import com.example.demo.service.IEmployeeService;
import com.example.demo.utils.ResultVoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author youngbear
 * @email youngbear@aliyun.com
 * @date 2019-07-21 18:43
 * @blog https://blog.csdn.net/next_second
 * @github https://github.com/YoungBear
 * @description
 */
@RestController
@RequestMapping(value = "employee", produces = MediaType.APPLICATION_JSON_VALUE)
public class EmployeeController {

    @Autowired
    private IEmployeeService employeeService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResultVo<Integer> add(@RequestBody EmployeeVo employeeVo) {
        try {
            return ResultVoUtils.success(employeeService.addEmployee(employeeVo));
        } catch (DemoException demoException) {
            return ResultVoUtils.error(demoException);
        }
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public ResultVo<Integer> deleteById(@PathVariable("id") Integer id) {
        try {
            return ResultVoUtils.success(employeeService.deleteEmployee(id));
        } catch (DemoException demoException) {
            return ResultVoUtils.error(demoException);
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResultVo<EmployeeVo> updateEmployee(@RequestBody EmployeeVo employeeVo) {
        try {
            return ResultVoUtils.success(employeeService.updateEmployee(employeeVo));
        } catch (DemoException demoException) {
            return ResultVoUtils.error(demoException);
        }
    }

    @RequestMapping(value = "/query/{id}", method = RequestMethod.GET)
    public ResultVo<EmployeeVo> queryById(@PathVariable("id") Integer id) {
        try {
            return ResultVoUtils.success(employeeService.queryEmployee(id));
        } catch (DemoException demoException) {
            return ResultVoUtils.error(demoException);
        }
    }

    @RequestMapping(value = "/queryAll", method = RequestMethod.GET)
    public ResultVo<EmployeeVo> queryAll() {
        try {
            return ResultVoUtils.success(employeeService.selectAll());
        } catch (DemoException demoException) {
            return ResultVoUtils.error(demoException);
        }
    }
}
