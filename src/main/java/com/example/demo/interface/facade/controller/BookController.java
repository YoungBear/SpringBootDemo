package com.example.demo.controller;

import com.example.demo.application.configuration.event.BookEvent;
import com.example.demo.entity.Book;
import com.example.demo.infrastructure.entity.ResultVo;
import com.example.demo.infrastructure.exception.DemoException;
import com.example.demo.application.service.IBookService;
import com.example.demo.infrastructure.utils.ResultVoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
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
public class BookController {

    @Autowired
    private IBookService bookService;

    @Resource
    private ApplicationContext applicationContext;

    @RequestMapping(value = "/book-list", method = RequestMethod.POST)
    public ResultVo<Book> bookList() {
        try {
            List<Book> books = bookService.bookList();
            return ResultVoUtils.success(books);
        } catch (DemoException demoException) {
            return ResultVoUtils.error(demoException);
        }

    }

    @RequestMapping(value = "one-book", method = RequestMethod.POST)
    public ResultVo<Book> oneBook(@RequestBody Book book) {
        try {
            Book book1 = bookService.oneBook(book.getName(), book.getAuthor(), book.getPublisher());
            BookEvent event = new BookEvent("BookEvent001");
            event.setBook(book1);
            applicationContext.publishEvent(event);
            return ResultVoUtils.success(book1);
        } catch (DemoException demoException) {
            return ResultVoUtils.error(demoException);
        }
    }

}
