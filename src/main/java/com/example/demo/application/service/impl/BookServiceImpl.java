package com.example.demo.application.service.impl;

import com.example.demo.entity.Book;
import com.example.demo.infrastructure.exception.DemoException;
import com.example.demo.application.service.IBookService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.example.demo.infrastructure.enums.ErrorEnum.BOOK_NAME_NULL_ERROR;

/**
 * @author youngbear
 * @email youngbear@aliyun.com
 * @date 2019-04-30 23:05
 * @blog https://blog.csdn.net/next_second
 * @github https://github.com/YoungBear
 * @description
 */
@Service
public class BookServiceImpl implements IBookService {
    @Override
    public List<Book> bookList() {
        List<Book> books = new ArrayList<>();
        Book b1 = new Book();
        b1.setName("数学之美");
        b1.setPublisher("人民邮电出版社");
        b1.setAuthor("吴军");
        Book b2 = new Book();
        b2.setName("重构 改善既有代码的设计");
        b2.setPublisher("人民邮电出版社");
        b2.setAuthor("Martin Fowler");
        Book b3 = new Book();
        b3.setName("机器学习实战");
        b3.setPublisher("人民邮电出版社");
        b3.setAuthor("Peter Harrington");
        Book b4 = new Book();
        b4.setName("Effective Java中文版");
        b4.setPublisher("机械工业出版社");
        b4.setAuthor("Joshua Bloch");
        books.add(b1);
        books.add(b2);
        books.add(b3);
        books.add(b4);
        return books;
    }

    @Override
    public Book oneBook(String name, String author, String publisher) {
        if (null == name) {
            throw new DemoException(BOOK_NAME_NULL_ERROR);
        }
        Book book = new Book();
        book.setName(name);
        book.setAuthor(author);
        book.setPublisher(publisher);
        return book;
    }
}
