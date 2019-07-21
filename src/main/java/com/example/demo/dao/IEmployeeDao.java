package com.example.demo.dao;

import com.example.demo.entity.Employee;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author youngbear
 * @email youngbear@aliyun.com
 * @date 2019-07-21 18:21
 * @blog https://blog.csdn.net/next_second
 * @github https://github.com/YoungBear
 * @description
 */
@Mapper
public interface IEmployeeDao {

    Employee findEmployeeById(Integer id);
}
