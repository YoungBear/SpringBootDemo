package com.example.demo.configuration;

import com.example.demo.entity.common.ResultVo;
import com.example.demo.enums.ErrorEnum;
import com.example.demo.exception.DemoException;
import com.example.demo.utils.ResultVoUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author youngbear
 * @email youngbear@aliyun.com
 * @date 2019-05-04 21:53
 * @blog https://blog.csdn.net/next_second
 * @github https://github.com/YoungBear
 * @description 全局异常处理
 */

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // 未知异常
    @ExceptionHandler(value = Exception.class)
    public ResultVo<String> defaultErrorHandler(Exception e) {
        LOGGER.error(e.getMessage(),e);
        return ResultVoUtils.error(ErrorEnum.UNKNOWN_ERROR);
    }

    // 自定义的异常
    @ExceptionHandler(value = DemoException.class)
    public ResultVo<String> errorHandler(DemoException e) {
        LOGGER.error(e.getMessage(),e);
        return ResultVoUtils.error(e.getErrorEnum());
    }
}
