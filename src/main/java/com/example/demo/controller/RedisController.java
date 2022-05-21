package com.example.demo.controller;

import com.example.demo.entity.common.Result;
import com.example.demo.exception.DemoException;
import com.example.demo.utils.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author youngbear
 * @email youngbear@aliyun.com
 * @date 2022/5/9 23:28
 * @blog https://blog.csdn.net/next_second
 * @github https://github.com/YoungBear
 * @description redis基础
 */
@RestController
@Api("redis 测试接口")
@RequestMapping(value = "redis", produces = MediaType.APPLICATION_JSON_VALUE)
public class RedisController {
    private static final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);

    @Autowired
    private StringRedisTemplate redisTemplate;

    @RequestMapping(value = "/setString", method = RequestMethod.POST)
    @ApiOperation("setString")
    public Result<String> setString(@ApiParam(name = "key", value = "key") @RequestParam(required = true) String key,
                                    @ApiParam(name = "value", value = "value") @RequestParam(required = true) String value) {

        redisTemplate.opsForValue().set(key, value);
        // 设置过期时间为1小时
        redisTemplate.expire(key, 3600L, TimeUnit.SECONDS);
        return ResultUtils.success("set successful.");
    }


    @RequestMapping(value = "/getString", method = RequestMethod.GET)
    @ApiOperation("getString")
    public Result<String> setString(@ApiParam(name = "key", value = "key") @RequestParam(required = true) String key) {
        String value = redisTemplate.opsForValue().get(key);
        Long expire = redisTemplate.getExpire(key);
        LOGGER.info("value: {}, expire: {}", value, expire);
        return ResultUtils.success("value: " + value + ", expire: " + expire);
    }
}
