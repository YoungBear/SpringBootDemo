package com.example.demo.utils;

import com.example.demo.entity.Book;
import com.example.demo.entity.common.Result;
import com.example.demo.entity.common.ResultVo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author youngbear
 * @email youngbear@aliyun.com
 * @date 2022/8/7 23:24
 * @blog https://blog.csdn.net/next_second
 * @github https://github.com/YoungBear
 * @description
 */
public class ResultVoUtilsTest {

    @Test
    public void successTest1() {
        Book book = new Book();
        book.setAuthor("吴军");
        book.setPublisher("人民邮电出版社");
        book.setName("数学之美");
        ResultVo<Book> resultVo = ResultVoUtils.success(book);
        Assertions.assertEquals(Result.SUCCESS_CODE, resultVo.getCode());
        Assertions.assertEquals(1, resultVo.getResult().getTotal().intValue());
    }

    @Test
    public void successTest2() {
        Book book = new Book();
        book.setAuthor("吴军");
        book.setPublisher("人民邮电出版社");
        book.setName("数学之美");
        Book book2 = new Book();
        book.setAuthor("Peter Harrington");
        book.setPublisher("人民邮电出版社");
        book.setName("机器学习实战");
        List<Book> bookList = new ArrayList<>();
        bookList.add(book);
        bookList.add(book2);
        ResultVo<Book> resultVo = ResultVoUtils.success(bookList);
        Assertions.assertEquals(Result.SUCCESS_CODE, resultVo.getCode());
        Assertions.assertEquals(2, resultVo.getResult().getTotal().intValue());
    }

    @Test
    public void successTest3() {
        Book book = new Book();
        book.setAuthor("吴军");
        book.setPublisher("人民邮电出版社");
        book.setName("数学之美");
        Book book2 = new Book();
        book.setAuthor("Peter Harrington");
        book.setPublisher("人民邮电出版社");
        book.setName("机器学习实战");
        List<Book> bookList = new ArrayList<>();
        bookList.add(book);
        bookList.add(book2);
        ResultVo<Book> resultVo = ResultVoUtils.success(10, bookList);
        Assertions.assertEquals(Result.SUCCESS_CODE, resultVo.getCode());
        Assertions.assertEquals(10, resultVo.getResult().getTotal().intValue());
    }
}
