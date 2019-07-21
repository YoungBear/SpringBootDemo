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
import org.springframework.web.bind.annotation.PathVariable;
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
@Api("Employee 接口")
@RequestMapping(value = "employee", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class EmployeeController {

    @Autowired
    private IEmployeeService employeeService;

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
}
