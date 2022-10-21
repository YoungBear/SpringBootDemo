package com.example.demo.infrastructure.utils;

import com.example.demo.infrastructure.entity.Result;
import com.example.demo.infrastructure.entity.ResultVo;
import com.example.demo.infrastructure.enums.ErrorEnum;
import com.example.demo.infrastructure.exception.DemoException;

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
public class ResultVoUtils {

    /**
     * 成功返回
     *
     * @param data 返回数据
     * @param <T> 数据类型
     * @return 统一的返回值
     */
    public static <T> ResultVo<T> success(T data) {
        ResultVo<T> resultVo = new ResultVo<>();
        resultVo.setCode(Result.SUCCESS_CODE);
        resultVo.setMsg(Result.SUCCESS_MESSAGE);
        Result<T> result = new Result<>();
        if (data != null) {
            List<T> dataList = new ArrayList<>(1);
            dataList.add(data);
            result.setTotal(1);
            result.setData(dataList);
        } else {
            result.setTotal(0);
        }
        resultVo.setResult(result);
        return resultVo;
    }

    public static <T> ResultVo<T> success(List<T> dataList) {
        ResultVo<T> resultVo = new ResultVo<>();
        resultVo.setCode(Result.SUCCESS_CODE);
        resultVo.setMsg(Result.SUCCESS_MESSAGE);
        Result<T> result = new Result<>();
        if (dataList != null && dataList.size() > 0) {
            result.setTotal(dataList.size());
            result.setData(dataList);
        } else {
            result.setTotal(0);
        }
        resultVo.setResult(result);
        return resultVo;
    }

    /**
     * 指定total，返回数据，用于分页场景
     *
     * @param total 总数量
     * @param dataList 当前数据
     * @param <T> 泛型参数
     * @return resultVo
     */
    public static <T> ResultVo<T> success(int total, List<T> dataList) {
        ResultVo<T> resultVo = success(dataList);
        resultVo.getResult().setTotal(total);
        return resultVo;
    }

    /**
     * 异常返回
     *
     * @param demoException
     * @param <T>
     * @return
     */
    public static <T> ResultVo<T> error(DemoException demoException) {
        ResultVo<T> resultVo = new ResultVo<>();
        resultVo.setCode(demoException.getErrorEnum().getErrorCode());
        resultVo.setMsg(demoException.getErrorEnum().getErrorMessage());
        return resultVo;
    }

    /**
     * 异常返回
     *
     * @param errorEnum
     * @param <T>
     * @return
     */
    public static <T> ResultVo<T> error(ErrorEnum errorEnum) {
        ResultVo<T> resultVo = new ResultVo<>();
        resultVo.setCode(errorEnum.getErrorCode());
        resultVo.setMsg(errorEnum.getErrorMessage());
        return resultVo;
    }
}
