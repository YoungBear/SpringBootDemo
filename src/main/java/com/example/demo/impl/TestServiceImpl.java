package com.example.demo.impl;

import com.example.demo.service.TestService;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

/**
 * 实现类，具体实现业务逻辑，这个类会很大
 */
@Service
public class TestServiceImpl implements TestService{
    @Override
    public String test2018() {
        return "Test 2018";
    }
}
