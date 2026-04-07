package com.example.demo.api.controller;

import com.example.demo.application.service.IHelloService;
import com.example.demo.domain.entity.Book;
import com.example.demo.domain.entity.EmployeeVo;
import com.example.demo.infrastructure.entity.ResultVo;
import com.example.demo.infrastructure.utils.RestTemplateUtils;
import com.example.demo.infrastructure.utils.ResultVoUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;

/**
 * @author youngbear
 * @email youngbear@aliyun.com
 * @date 2018/11/28 21:55
 * @blog https://blog.csdn.net/next_second
 * @github https://github.com/YoungBear
 * @description
 */
@RestController
@RequestMapping(value = "hello", produces = MediaType.APPLICATION_JSON_VALUE)
public class HelloController {
    private static final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);

    @Autowired
    private IHelloService helloService;

    @Resource
    private RestTemplateUtils restTemplateUtils;

    @Value("${app.base-url:http://localhost:8888}")
    private String baseUrl;

    @RequestMapping(value = "/hi", method = RequestMethod.GET)
    public ResultVo<String> hi(@RequestParam(required = false) String name) {
        String hi = helloService.hi(name);
        return ResultVoUtils.success(hi);
    }

    @GetMapping(value = "/request-get")
    public ResultVo<Void> requestGet() {
        String url = baseUrl + "/employee/query/2";
        ResultVo<EmployeeVo> resultVo = restTemplateUtils.get(url,
                new EmployeeVoParameterizedTypeReference());
        LOGGER.info("code: {}", resultVo.getCode());
        EmployeeVo employeeVo = resultVo.getResult().getData().get(0);
        return ResultVoUtils.success(null);
    }

    @GetMapping(value = "/request-post")
    public ResultVo<Void> requestPost() {
        Book book = new Book();
        book.setAuthor("吴军");
        book.setPublisher("人民邮电出版社");
        book.setName("数学之美");
        String url = baseUrl + "/v1/book/one-book";
        ResultVo<Book> resultVo = restTemplateUtils.post(url,
                book, new BookParameterizedTypeReference());
        LOGGER.info("code: {}", resultVo.getCode());
        Book responseBook = resultVo.getResult().getData().get(0);
        return ResultVoUtils.success(null);
    }

    private static class EmployeeVoParameterizedTypeReference extends ParameterizedTypeReference<ResultVo<EmployeeVo>> {
    }

    private static class BookParameterizedTypeReference extends ParameterizedTypeReference<ResultVo<Book>> {
    }
}
