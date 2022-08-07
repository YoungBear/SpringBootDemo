package com.example.demo.controller;

import com.example.demo.entity.DivideEntity;
import com.example.demo.entity.common.ResultVo;
import com.example.demo.utils.ResultVoUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author youngbear
 * @email youngbear@aliyun.com
 * @date 2019-05-04 22:00
 * @blog https://blog.csdn.net/next_second
 * @github https://github.com/YoungBear
 * @description
 */

@RestController
@RequestMapping(value = "calculator", produces = MediaType.APPLICATION_JSON_VALUE)
public class CalculatorController {

    @RequestMapping(value = "divide", method = RequestMethod.POST)
    @ApiOperation(value = "除法运算")
    public ResultVo<String> divide(@RequestBody DivideEntity divideEntity) {
        int dividend = divideEntity.getDividend();
        int divisor = divideEntity.getDivisor();
        int quotients = dividend / divisor;
        return ResultVoUtils.success(String.valueOf(quotients));

    }


}
