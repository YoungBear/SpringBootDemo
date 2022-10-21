package com.example.demo.application.configuration.event;

import com.example.demo.entity.Book;
import org.springframework.context.ApplicationEvent;

/**
 * @author youngbear
 * @email youngbear@aliyun.com
 * @date 2021/10/3 14:00
 * @blog https://blog.csdn.net/next_second
 * @github https://github.com/YoungBear
 * @description
 */
public class BookEvent extends ApplicationEvent {
    private static final long serialVersionUID = -2137894125601150872L;
    private Book book;

    public BookEvent(Object source) {
        super(source);
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
