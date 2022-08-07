package com.example.demo.entity;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * @author youngbear
 * @email youngbear@aliyun.com
 * @date 2019-07-21 18:18
 * @blog https://blog.csdn.net/next_second
 * @github https://github.com/YoungBear
 * @description
 */
@Data
public class EmployeeVo {
    private Integer id;
    private String name;
    private LocalDateTime hireDate;
    private Float salary;
    private Integer deptNo;


    public long getHireDateTimestamp() {
        return hireDate.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }
}
