package com.example.demo.controller;

import com.example.demo.entity.common.Result;
import com.example.demo.exception.DemoException;
import com.example.demo.service.IHelloService;
import com.example.demo.utils.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author youngbear
 * @email youngbear@aliyun.com
 * @date 2018/11/28 21:55
 * @blog https://blog.csdn.net/next_second
 * @github https://github.com/YoungBear
 * @description
 */
@RestController
@Api("Hello 测试接口")
@RequestMapping(value = "hello", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class HelloController {
    private static final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);

    @Autowired
    private IHelloService helloService;

    @RequestMapping(value = "/hi", method = RequestMethod.GET)
    @ApiOperation("测试 hi")
    public Result<String> hi(
            @ApiParam(name = "name", value = "姓名") @RequestParam(required = false) String name) {

        try {
            String hi = helloService.hi(name);

            return ResultUtils.success(hi);
        } catch (DemoException demoException) {
            return ResultUtils.error(demoException);
        }

    }

}
