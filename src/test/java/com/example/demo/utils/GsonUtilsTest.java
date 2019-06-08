package com.example.demo.utils;

import com.example.demo.entity.Book;
import com.example.demo.entity.common.Result;
import org.junit.Assert;
import org.junit.Test;

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

        Result<Map> mapResult = GsonUtils.parseString(json, Map.class);
        Assert.assertEquals(0, mapResult.getCode().intValue());
        Assert.assertEquals(4, mapResult.getResult().getTotal().intValue());

        List<Map> mapList = mapResult.getResult().getData();
        Assert.assertEquals(4, mapList.size());

        Result<Book> bookResult = GsonUtils.parseString(json, Book.class);
        Assert.assertEquals(0, bookResult.getCode().intValue());
        Assert.assertEquals(4, bookResult.getResult().getTotal().intValue());
        List<Book> data = bookResult.getResult().getData();

        // 排序
        data.sort((a, b) -> a.getName().compareTo(b.getName()));

        Assert.assertEquals("Effective Java中文版", data.get(0).getName());
        Assert.assertEquals("Joshua Bloch", data.get(0).getAuthor());
        Assert.assertEquals("机械工业出版社", data.get(0).getPublisher());
        Assert.assertEquals("吴军", data.get(1).getAuthor());
        Assert.assertEquals("人民邮电出版社", data.get(2).getPublisher());
        Assert.assertEquals("重构 改善既有代码的设计", data.get(3).getName());
    }
}
