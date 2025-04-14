package com.example.demo.application.configuration.listener;

import com.example.demo.application.configuration.event.BookEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author youngbear
 * @email youngbear@aliyun.com
 * @date 2021/10/3 13:59
 * @blog https://blog.csdn.net/next_second
 * @github https://github.com/YoungBear
 * @description
 */
@Component
public class BookApplicationListener implements ApplicationListener<BookEvent> {
    private static final Logger LOGGER = LoggerFactory.getLogger(BookApplicationListener.class);

    @Override
    public void onApplicationEvent(BookEvent bookEvent) {
        LOGGER.info("onApplicationEvent, bookEvent, bookName: {}", bookEvent.getBook().getName());
    }
}
