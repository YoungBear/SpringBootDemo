package com.example.demo.entity;

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
public class EmployeeEntity {
    private Integer id;
    private String name;
    private LocalDateTime hireDate;
    private Float salary;
    private Integer deptNo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDateTime hireDate) {
        this.hireDate = hireDate;
    }

    public Float getSalary() {
        return salary;
    }

    public void setSalary(Float salary) {
        this.salary = salary;
    }

    public Integer getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(Integer deptNo) {
        this.deptNo = deptNo;
    }

    public long getHireDateTimestamp() {
        return hireDate.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }
}
