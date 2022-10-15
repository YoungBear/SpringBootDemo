package com.example.demo.enums;

/**
 * @author youngbear
 * @email youngbear@aliyun.com
 * @date 2019-04-30 22:35
 * @blog https://blog.csdn.net/next_second
 * @github https://github.com/YoungBear
 * @description 错误定义：错误码及错误信息
 */
public enum ErrorEnum {

    UNKNOWN_ERROR(-1, "unknown error."),
    HTTP_REQUEST_ERROR(9999, "http request error"),
    BOOK_NAME_NULL_ERROR(10001, "book name is null."),
    HELLO_NAME_NULL_ERROR(20001, "hi name is null.");


    final Integer errorCode;
    final String errorMessage;

    ErrorEnum(Integer errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
