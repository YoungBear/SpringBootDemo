package com.example.demo.infrastructure.entity;

import lombok.Data;

import java.util.List;

/**
 * @author youngbear
 * @email youngbear@aliyun.com
 * @date 2019-04-30 22:51
 * @blog https://blog.csdn.net/next_second
 * @github https://github.com/YoungBear
 * @description 返回具体格式
 */
@Data
public class Result<T> {
    public static final Integer SUCCESS_CODE = 0;

    public static final String SUCCESS_MESSAGE = "request successful.";

    /**
     * 数据总数
     */
    private Integer total;
    /**
     * 当前页数据
     */
    private List<T> data;
}
