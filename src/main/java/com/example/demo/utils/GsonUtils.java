package com.example.demo.utils;

import com.example.demo.entity.common.Result;
import com.google.gson.Gson;

import java.lang.reflect.Type;

/**
 * @author youngbear
 * @email youngbear@aliyun.com
 * @date 2019-05-14 22:44
 * @blog https://blog.csdn.net/next_second
 * @github https://github.com/YoungBear
 * @description Gson 工具类
 */
public class GsonUtils {
    private static final Gson GSON = new Gson();

    /**
     * Gson 解析通用数据格式
     * @param json
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> Result<T> parseString(String json, Class<T> clazz) {
        Type type = new ParameterizedTypeImpl(Result.class, new Class[]{clazz});
        Result<T> result = GSON.fromJson(json, type);
        return result;
    }
}
