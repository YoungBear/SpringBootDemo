package com.example.demo.utils;

import com.example.demo.enums.ErrorEnum;
import com.example.demo.exception.DemoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * Http请求工具类
 *
 * @author youngbear
 * @email youngbear@aliyun.com
 * @date 2022/8/7 23:32
 * @blog https://blog.csdn.net/next_second
 * @github https://github.com/YoungBear
 * @description
 */
@Component
public class RestTemplateUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(RestTemplateUtils.class);

    @Resource
    private RestTemplate restTemplate;

    /**
     * http get 请求
     *
     * @param url url
     * @param responseType response类型
     * @param <T> 类型参数
     * @return response body
     */
    public <T> T get(String url, ParameterizedTypeReference<T> responseType) {
        return http(url, HttpMethod.GET, null, responseType);
    }

    /**
     * http post 请求
     *
     * @param url url
     * @param request 请求体
     * @param responseType response类型
     * @param <T> 类型参数
     * @return response body
     */
    public <T> T post(String url, Object request, ParameterizedTypeReference<T> responseType) {
        return http(url, HttpMethod.POST, request, responseType);
    }

    /**
     * http 请求
     *
     * @param url url
     * @param httpMethod method
     * @param request request
     * @param responseType response类型
     * @param <T> 类型参数
     * @return response body
     */
    public <T> T http(String url, HttpMethod httpMethod, Object request, ParameterizedTypeReference<T> responseType) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> requestData = new HttpEntity<>(request, httpHeaders);
        ResponseEntity<T> responseEntity;
        try {
            responseEntity = restTemplate.exchange(url, httpMethod, requestData, responseType);
        } catch (RestClientException e) {
            LOGGER.error("RestClientException when exchange", e);
            throw new DemoException(e, ErrorEnum.HTTP_REQUEST_ERROR);
        }
        if (responseEntity.getStatusCode() != HttpStatus.OK) {
            LOGGER.error("http request has response but status is not ok");
            throw new DemoException(ErrorEnum.HTTP_REQUEST_ERROR);
        }
        T body = responseEntity.getBody();
        if (body == null) {
            LOGGER.error("http request has response but response body is null");
            throw new DemoException(ErrorEnum.HTTP_REQUEST_ERROR);
        }
        return body;
    }

}
