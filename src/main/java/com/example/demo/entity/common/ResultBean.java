package com.example.demo.entity.common;

import java.util.List;

/**
 * @author youngbear
 * @email youngbear@aliyun.com
 * @date 2019-04-30 22:51
 * @blog https://blog.csdn.net/next_second
 * @github https://github.com/YoungBear
 * @description
 */
public class ResultBean<T> {

    private Integer total;
    private List<T> data;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
