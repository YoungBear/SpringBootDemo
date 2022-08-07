package com.example.demo.controller;

import com.example.demo.entity.Book;
import com.example.demo.entity.EmployeeVo;
import com.example.demo.entity.common.ResultVo;
import com.example.demo.exception.DemoException;
import com.example.demo.service.IHelloService;
import com.example.demo.utils.RestTemplateUtils;
import com.example.demo.utils.ResultVoUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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
@RequestMapping(value = "hello", produces = MediaType.APPLICATION_JSON_VALUE)
public class HelloController {
    private static final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);

    @Autowired
    private IHelloService helloService;

    @Resource
    private RestTemplateUtils restTemplateUtils;

    @RequestMapping(value = "/hi", method = RequestMethod.GET)
    @ApiOperation("测试 hi")
    public ResultVo<String> hi(@ApiParam(name = "name", value = "姓名") @RequestParam(required = false) String name) {

        try {
            String hi = helloService.hi(name);
            return ResultVoUtils.success(hi);
        } catch (DemoException demoException) {
            return ResultVoUtils.error(demoException);
        }

    }

    @GetMapping(value = "/request-get")
    public ResultVo<Void> requestGet() {
        String url = "http://localhost:8888/employee/query/2";
        ResultVo<EmployeeVo> resultVo = restTemplateUtils.get(url,
                new ParameterizedTypeReference<ResultVo<EmployeeVo>>() {
                });
        LOGGER.info("code: {}", resultVo.getCode());
        return ResultVoUtils.success(null);
    }

    @GetMapping(value = "/request-post")
    public ResultVo<Void> requestPost() {
        Book book = new Book();
        book.setAuthor("吴军");
        book.setPublisher("人民邮电出版社");
        book.setName("数学之美");
        String url = "http://localhost:8888/v1/book/one-book";
        ResultVo<Book> resultVo = restTemplateUtils.post(url,
                book,
                new ParameterizedTypeReference<ResultVo<Book>>() {
                });
        LOGGER.info("code: {}", resultVo.getCode());
        return ResultVoUtils.success(null);
    }


}
