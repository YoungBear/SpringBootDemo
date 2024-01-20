package com.example.demo.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

/**
 * @author youngbear
 * @email youngbear@aliyun.com
 * @date 2024-01-20 20:43
 * @blog <a href="https://blog.csdn.net/next_second">...</a>
 * @github <a href="https://github.com/YoungBear">...</a>
 * @description 文件读取工具类
 */
public class ResourceFileReadUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResourceFileReadUtils.class);

    /**
     * 读取字符串
     *
     * @param path 文件路径 如 json/employee/EmployeeAdd.json
     * @return 文件内容
     */
    public static String readString(String path) {
        try {
            File file = new File(ResourceFileReadUtils.class.getClassLoader().getResource(path).getFile());
            return new String(Files.readAllBytes(file.toPath()), StandardCharsets.UTF_8);
        } catch (Exception e) {
            LOGGER.error("readString exception", e);
        }
        return "";
    }

    /**
     * 解析为实体类
     *
     * @param path 文件路径
     * @param clazz class
     * @param <T> 类型
     * @return 实体类
     */
    public static <T> T readObject(String path, Class<T> clazz) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(readString(path), clazz);
        } catch (Exception e) {
            LOGGER.error("readObject exception", e);
        }
        return null;
    }

}
