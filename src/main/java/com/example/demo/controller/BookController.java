package com.example.demo.controller;

import com.example.demo.configuration.event.BookEvent;
import com.example.demo.entity.Book;
import com.example.demo.entity.common.Result;
import com.example.demo.exception.DemoException;
import com.example.demo.service.IBookService;
import com.example.demo.utils.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.annotation.Resources;
import java.util.List;

/**
 * @author youngbear
 * @email youngbear@aliyun.com
 * @date 2018/12/10 23:07
 * @blog https://blog.csdn.net/next_second
 * @github https://github.com/YoungBear
 * @description
 */
@RestController
@RequestMapping(value = "/v1/book", produces = MediaType.APPLICATION_JSON_VALUE)
@Api("Book 接口")
public class BookController {

    @Autowired
    private IBookService bookService;

    @Resource
    private ApplicationContext applicationContext;

    @RequestMapping(value = "/book-list", method = RequestMethod.POST)
    @ApiOperation("返回测试的 book 列表")
    public Result<Book> bookList() {
        try {
            List<Book> books = bookService.bookList();
            return ResultUtils.success(books);
        } catch (DemoException demoException) {
            return ResultUtils.error(demoException);
        }

    }

    @RequestMapping(value = "one-book", method = RequestMethod.POST)
    public Result<Book> oneBook(@RequestBody Book book) {
        try {
            Book book1 = bookService.oneBook(book.getName(), book.getAuthor(), book.getPublisher());
            BookEvent event = new BookEvent("BookEvent001");
            event.setBook(book1);
            applicationContext.publishEvent(event);
            return ResultUtils.success(book1);
        } catch (DemoException demoException) {
            return ResultUtils.error(demoException);
        }
    }

}
