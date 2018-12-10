package com.example.demo.controller;

import com.example.demo.mode.Book;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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
@RequestMapping("/v1/book")
public class BookController {

    @RequestMapping(value = "/books", method = RequestMethod.POST,
            produces = "application/json;charset=UTF-8")
    public List<Book> test() {
        List<Book> books = new ArrayList<>();
        Book b1 = new Book();
        b1.setName("数学之美");
        b1.setPublisher("人民邮电出版社");
        b1.setAuther("吴军");
        Book b2 = new Book();
        b2.setName("重构 改善既有代码的设计");
        b2.setPublisher("人民邮电出版社");
        b2.setAuther("Martin Fowler");
        Book b3 = new Book();
        b3.setName("机器学习实战");
        b3.setPublisher("人民邮电出版社");
        b3.setAuther("Peter Harrington");
        Book b4 = new Book();
        b4.setName("Effective Java中文版");
        b4.setPublisher("机械工业出版社");
        b4.setAuther("Joshua Bloch");
        books.add(b1);
        books.add(b2);
        books.add(b3);
        books.add(b4);
        return books;
    }

}
