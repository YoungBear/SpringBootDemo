package com.example.demo.controller;

import com.example.demo.entity.EmployeeVo;
import com.example.demo.entity.common.ResultVo;
import com.example.demo.exception.DemoException;
import com.example.demo.service.IEmployeeService;
import com.example.demo.utils.ResultVoUtils;
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
    public ResultVo<Integer> add(
            @ApiParam(name = "employeeEntity", value = "employee 信息") @RequestBody EmployeeVo employeeVo) {
        try {
            return ResultVoUtils.success(employeeService.addEmployee(employeeVo));
        } catch (DemoException demoException) {
            return ResultVoUtils.error(demoException);
        }
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ApiOperation("根据id删除Employee")
    public ResultVo<Integer> deleteById(
            @ApiParam(name = "id", value = "employee id") @PathVariable("id") Integer id) {
        try {
            return ResultVoUtils.success(employeeService.deleteEmployee(id));
        } catch (DemoException demoException) {
            return ResultVoUtils.error(demoException);
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ApiOperation("更新 Employee")
    public ResultVo<EmployeeVo> updateEmployee(
            @ApiParam(name = "employeeEntity", value = "employee 信息") @RequestBody EmployeeVo employeeVo) {
        try {
            return ResultVoUtils.success(employeeService.updateEmployee(employeeVo));
        } catch (DemoException demoException) {
            return ResultVoUtils.error(demoException);
        }
    }

    @RequestMapping(value = "/query/{id}", method = RequestMethod.GET)
    @ApiOperation("根据id查询Employee")
    public ResultVo<EmployeeVo> queryById(
            @ApiParam(name = "id", value = "employee id") @PathVariable("id") Integer id) {
        try {
            return ResultVoUtils.success(employeeService.queryEmployee(id));
        } catch (DemoException demoException) {
            return ResultVoUtils.error(demoException);
        }
    }

    @RequestMapping(value = "/queryAll", method = RequestMethod.GET)
    @ApiOperation("查询Employee列表")
    public ResultVo<EmployeeVo> queryAll() {
        try {
            return ResultVoUtils.success(employeeService.selectAll());
        } catch (DemoException demoException) {
            return ResultVoUtils.error(demoException);
        }
    }
}
