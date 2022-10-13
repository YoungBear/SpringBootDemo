package com.example.demo.utils;

import com.example.demo.entity.Book;
import com.example.demo.entity.common.ResultVo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

/**
 * @author youngbear
 * @email youngbear@aliyun.com
 * @date 2019-05-14 22:54
 * @blog https://blog.csdn.net/next_second
 * @github https://github.com/YoungBear
 * @description Gson 工具类测试
 */
public class GsonUtilsTest {

    @Test
    public void parseStringTest() {
        String json = "{\n" +
                "  \"code\": 0,\n" +
                "  \"msg\": \"request successful.\",\n" +
                "  \"result\": {\n" +
                "    \"total\": 4,\n" +
                "    \"data\": [\n" +
                "      {\n" +
                "        \"name\": \"数学之美\",\n" +
                "        \"publisher\": \"人民邮电出版社\",\n" +
                "        \"author\": \"吴军\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"name\": \"重构 改善既有代码的设计\",\n" +
                "        \"publisher\": \"人民邮电出版社\",\n" +
                "        \"author\": \"Martin Fowler\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"name\": \"机器学习实战\",\n" +
                "        \"publisher\": \"人民邮电出版社\",\n" +
                "        \"author\": \"Peter Harrington\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"name\": \"Effective Java中文版\",\n" +
                "        \"publisher\": \"机械工业出版社\",\n" +
                "        \"author\": \"Joshua Bloch\"\n" +
                "      }\n" +
                "    ]\n" +
                "  }\n" +
                "}";

        ResultVo<Map> mapResultVo = GsonUtils.parseString(json, Map.class);
        Assertions.assertEquals(0, mapResultVo.getCode().intValue());
        Assertions.assertEquals(4, mapResultVo.getResult().getTotal().intValue());

        List<Map> mapList = mapResultVo.getResult().getData();
        Assertions.assertEquals(4, mapList.size());

        ResultVo<Book> bookResultVo = GsonUtils.parseString(json, Book.class);
        Assertions.assertEquals(0, bookResultVo.getCode().intValue());
        Assertions.assertEquals(4, bookResultVo.getResult().getTotal().intValue());
        List<Book> data = bookResultVo.getResult().getData();

        // 排序
        data.sort((a, b) -> a.getName().compareTo(b.getName()));

        Assertions.assertEquals("Effective Java中文版", data.get(0).getName());
        Assertions.assertEquals("Joshua Bloch", data.get(0).getAuthor());
        Assertions.assertEquals("机械工业出版社", data.get(0).getPublisher());
        Assertions.assertEquals("吴军", data.get(1).getAuthor());
        Assertions.assertEquals("人民邮电出版社", data.get(2).getPublisher());
        Assertions.assertEquals("重构 改善既有代码的设计", data.get(3).getName());
    }
}
