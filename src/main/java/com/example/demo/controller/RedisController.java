package com.example.demo.controller;

import com.example.demo.entity.common.ResultVo;
import com.example.demo.utils.ResultVoUtils;
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
@RequestMapping(value = "redis", produces = MediaType.APPLICATION_JSON_VALUE)
public class RedisController {
    private static final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);

    @Autowired
    private StringRedisTemplate redisTemplate;

    @RequestMapping(value = "/setString", method = RequestMethod.POST)
    public ResultVo<String> setString(@RequestParam(required = true) String key, @RequestParam(required = true) String value) {

        redisTemplate.opsForValue().set(key, value);
        // 设置过期时间为1小时
        redisTemplate.expire(key, 3600L, TimeUnit.SECONDS);
        return ResultVoUtils.success("set successful.");
    }


    @RequestMapping(value = "/getString", method = RequestMethod.GET)
    public ResultVo<String> setString(@RequestParam(required = true) String key) {
        String value = redisTemplate.opsForValue().get(key);
        Long expire = redisTemplate.getExpire(key);
        LOGGER.info("value: {}, expire: {}", value, expire);
        return ResultVoUtils.success("value: " + value + ", expire: " + expire);
    }
}
