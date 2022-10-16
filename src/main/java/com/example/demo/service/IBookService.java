package com.example.demo.service;

import com.example.demo.entity.Book;

import java.util.List;

/**
 * @author youngbear
 * @email youngbear@aliyun.com
 * @date 2019-04-30 23:01
 * @blog https://blog.csdn.net/next_second
 * @github https://github.com/YoungBear
 * @description
 */
public interface IBookService {

    /**
     * 返回测试列表
     *
     * @return
     */
    List<Book> bookList();

    /**
     * 构造一个书的对象
     *
     * @param name
     * @param author
     * @param publisher
     * @return
     */
    Book oneBook(String name, String author, String publisher);

}
