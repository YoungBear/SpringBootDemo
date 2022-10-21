package com.example.demo.application.service.impl;

import com.example.demo.application.service.IHelloService;
import com.example.demo.infrastructure.exception.DemoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import static com.example.demo.infrastructure.enums.ErrorEnum.HELLO_NAME_NULL_ERROR;

/**
 * @author youngbear
 * @email youngbear@aliyun.com
 * @date 2019-04-30 23:17
 * @blog https://blog.csdn.net/next_second
 * @github https://github.com/YoungBear
 * @description
 */
@Service
public class HelloServiceImpl implements IHelloService {
    private static final Logger LOGGER = LoggerFactory.getLogger(HelloServiceImpl.class);

    @Override
    public String hi(String name) {
        LOGGER.trace("trace Hello {}", name);
        LOGGER.debug("debug Hello {}", name);
        LOGGER.info("info Hello {}", name);
        LOGGER.warn("warn Hello {}", name);
        LOGGER.error("error Hello {}", name);
        if (null == name) {
            throw new DemoException(HELLO_NAME_NULL_ERROR);
        }
        return "Hello " + name;
    }
}
