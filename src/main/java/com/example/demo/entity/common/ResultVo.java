package com.example.demo.entity.common;

import lombok.Data;

/**
 * @author youngbear
 * @email youngbear@aliyun.com
 * @date 2019-04-30 21:38
 * @blog https://blog.csdn.net/next_second
 * @github https://github.com/YoungBear
 * @description 统一返回json格式Vo类
 */
@Data
public class ResultVo<T> {

    /**
     * 错误码
     */
    private Integer code;

    /**
     * 提示信息
     */
    private String msg;

    /**
     * 数据
     */
    private Result<T> result;
}
