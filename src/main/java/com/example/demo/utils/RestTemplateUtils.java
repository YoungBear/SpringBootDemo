package com.example.demo.utils;

import com.example.demo.entity.common.ResultVo;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author youngbear
 * @email youngbear@aliyun.com
 * @date 2022/8/7 23:32
 * @blog https://blog.csdn.net/next_second
 * @github https://github.com/YoungBear
 * @description
 */
@Component
public class RestTemplateUtils {

    @Resource
    private RestTemplate restTemplate;

    public <T> ResultVo<T> get(String url, ParameterizedTypeReference<ResultVo<T>> responseType) {
        return http(url, HttpMethod.GET, null, responseType);
    }

    public <T> ResultVo<T> post(String url, Object request, ParameterizedTypeReference<ResultVo<T>> responseType) {
        return http(url, HttpMethod.POST, request, responseType);
    }

    public <T> ResultVo<T> http(String url, HttpMethod httpMethod, Object request, ParameterizedTypeReference<ResultVo<T>> responseType) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> requestData = new HttpEntity<>(request, httpHeaders);
        return restTemplate.exchange(url, httpMethod, requestData, responseType).getBody();
    }
}
