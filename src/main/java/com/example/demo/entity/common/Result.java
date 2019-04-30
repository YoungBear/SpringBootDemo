package com.example.demo.entity.common;

/**
 * @author youngbear
 * @email youngbear@aliyun.com
 * @date 2019-04-30 21:38
 * @blog https://blog.csdn.net/next_second
 * @github https://github.com/YoungBear
 * @description 统一返回json格式
 */
public class Result<T> {

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
    private ResultBean<T> result;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ResultBean<T> getResult() {
        return result;
    }

    public void setResult(ResultBean<T> result) {
        this.result = result;
    }
}
