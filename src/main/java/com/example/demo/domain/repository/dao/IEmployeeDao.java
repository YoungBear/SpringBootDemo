package com.example.demo.domain.repository.dao;

import com.example.demo.entity.EmployeeVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

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

    Integer add(EmployeeVo employeeVo);

    Integer delete(Integer id);

    void update(EmployeeVo employeeVo);

    EmployeeVo findEmployeeById(Integer id);

    List<EmployeeVo> selectAll();
}
