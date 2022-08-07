package com.example.demo.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author youngbear
 * @email youngbear@aliyun.com
 * @date 2019-05-04 22:08
 * @blog https://blog.csdn.net/next_second
 * @github https://github.com/YoungBear
 * @description
 */
@Data
@ApiModel(value = "除法运算输入对象",description = "除法运算输入对象描述")
public class DivideEntity {

    @ApiModelProperty(value = "被除数", required = true)
    private int dividend;
    @ApiModelProperty(value = "除数", required = true)
    private int divisor;
}
