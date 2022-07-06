package com.example.demo.controller;

import com.example.demo.entity.EmployeeEntity;
import com.example.demo.entity.common.Result;
import com.example.demo.exception.DemoException;
import com.example.demo.service.IEmployeeService;
import com.example.demo.utils.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @author youngbear
 * @email youngbear@aliyun.com
 * @date 2019-07-21 18:43
 * @blog https://blog.csdn.net/next_second
 * @github https://github.com/YoungBear
 * @description
 */
@RestController
@Api("Employee 接口")
@RequestMapping(value = "employee", produces = MediaType.APPLICATION_JSON_VALUE)
public class EmployeeController {

    @Autowired
    private IEmployeeService employeeService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ApiOperation("根据id查询Employee")
    public Result<Integer> add(
            @ApiParam(name = "employeeEntity", value = "employee 信息") @RequestBody EmployeeEntity employeeEntity) {
        try {
            return ResultUtils.success(employeeService.addEmployee(employeeEntity));
        } catch (DemoException demoException) {
            return ResultUtils.error(demoException);
        }
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ApiOperation("根据id删除Employee")
    public Result<Integer> deleteById(
            @ApiParam(name = "id", value = "employee id") @PathVariable("id") Integer id) {
        try {
            return ResultUtils.success(employeeService.deleteEmployee(id));
        } catch (DemoException demoException) {
            return ResultUtils.error(demoException);
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ApiOperation("更新 Employee")
    public Result<EmployeeEntity> updateEmployee(
            @ApiParam(name = "employeeEntity", value = "employee 信息") @RequestBody EmployeeEntity employeeEntity) {
        try {
            return ResultUtils.success(employeeService.updateEmployee(employeeEntity));
        } catch (DemoException demoException) {
            return ResultUtils.error(demoException);
        }
    }

    @RequestMapping(value = "/query/{id}", method = RequestMethod.GET)
    @ApiOperation("根据id查询Employee")
    public Result<EmployeeEntity> queryById(
            @ApiParam(name = "id", value = "employee id") @PathVariable("id") Integer id) {
        try {
            return ResultUtils.success(employeeService.queryEmployee(id));
        } catch (DemoException demoException) {
            return ResultUtils.error(demoException);
        }
    }

    @RequestMapping(value = "/queryAll", method = RequestMethod.GET)
    @ApiOperation("查询Employee列表")
    public Result<EmployeeEntity> queryAll() {
        try {
            return ResultUtils.success(employeeService.selectAll());
        } catch (DemoException demoException) {
            return ResultUtils.error(demoException);
        }
    }
}
