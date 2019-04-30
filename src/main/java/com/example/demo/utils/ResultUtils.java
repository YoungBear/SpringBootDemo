package com.example.demo.utils;

import com.example.demo.entity.common.Result;
import com.example.demo.entity.common.ResultBean;
import com.example.demo.enums.ErrorEnum;
import com.example.demo.exception.DemoException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author youngbear
 * @email youngbear@aliyun.com
 * @date 2019-04-30 22:19
 * @blog https://blog.csdn.net/next_second
 * @github https://github.com/YoungBear
 * @description
 */
public class ResultUtils {

    /**
     * 成功返回
     *
     * @param data 返回数据
     * @param <T>  数据类型
     * @return 统一的返回值
     */
    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.setCode(0);
        result.setMsg("request successful.");
        ResultBean<T> resultBean = new ResultBean<>();
        resultBean.setTotal(1);
        List<T> dataList = new ArrayList<>(1);
        dataList.add(data);
        resultBean.setData(dataList);
        result.setResult(resultBean);
        return result;
    }

    public static <T> Result<T> success(List<T> dataList){
        Result<T> result = new Result<>();
        result.setCode(0);
        result.setMsg("request successful.");
        ResultBean<T> resultBean = new ResultBean<>();
        resultBean.setTotal(dataList.size());
        resultBean.setData(dataList);
        result.setResult(resultBean);
        return result;
    }
    /**
     * 异常返回
     *
     * @param demoException
     * @param <T>
     * @return
     */
    public static <T> Result<T> error(DemoException demoException) {
        Result<T> result = new Result<>();
        result.setCode(demoException.getErrorEnum().getErrorCode());
        result.setMsg(demoException.getErrorEnum().getErrorMessage());
        return result;
    }

    /**
     * 异常返回
     * @param errorEnum
     * @param <T>
     * @return
     */
    public static <T> Result<T> error(ErrorEnum errorEnum) {
        Result<T> result = new Result<>();
        result.setCode(errorEnum.getErrorCode());
        result.setMsg(errorEnum.getErrorMessage());
        return result;
    }
}
