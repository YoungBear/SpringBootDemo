package com.example.demo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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
public class HelloController {
    private static final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation("测试 hello")
    public String hello() {
        LOGGER.trace("trace Hello");
        LOGGER.debug("debug Hello");
        LOGGER.info("info Hello");
        LOGGER.warn("warn Hello");
        LOGGER.error("error Hello");
        return "Hello World!";
    }
}
