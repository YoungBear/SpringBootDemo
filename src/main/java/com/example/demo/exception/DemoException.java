package com.example.demo.exception;

import com.example.demo.enums.ErrorEnum;

/**
 * @author youngbear
 * @email youngbear@aliyun.com
 * @date 2019-04-30 22:31
 * @blog https://blog.csdn.net/next_second
 * @github https://github.com/YoungBear
 * @description 统一异常
 */
public class DemoException extends RuntimeException {

    private static final long serialVersionUID = 1905532578117424643L;
    private final ErrorEnum errorEnum;

    public DemoException(ErrorEnum errorEnum) {
        this.errorEnum = errorEnum;
    }

    public DemoException(Throwable cause, ErrorEnum errorEnum) {
        super(cause);
        this.errorEnum = errorEnum;
    }


    public ErrorEnum getErrorEnum() {
        return errorEnum;
    }
}
