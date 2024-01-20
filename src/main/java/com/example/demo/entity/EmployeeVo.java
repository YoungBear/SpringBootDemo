package com.example.demo.entity;

import lombok.Data;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * @author youngbear
 * @email youngbear@aliyun.com
 * @date 2019-07-21 18:18
 * @blog https://blog.csdn.net/next_second
 * @github https://github.com/YoungBear
 * @description 雇员实体类
 */
@Data
public class EmployeeVo {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private Integer id;
    private String name;
    private Long hireDate;
    private Float salary;
    private Integer deptNo;

    /**
     * 返回格式化的日期
     *
     * @return 日期
     */
    public String getHireDateFormat() {
        return Instant.ofEpochMilli(hireDate).atZone(ZoneId.systemDefault()).toLocalDate().format(DATE_FORMATTER);
    }

}
