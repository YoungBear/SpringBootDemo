package com.example.demo.controller;

import com.example.demo.entity.EmployeeVo;
import com.example.demo.entity.common.Result;
import com.example.demo.enums.ErrorEnum;
import com.example.demo.exception.DemoException;
import com.example.demo.service.IEmployeeService;
import com.example.demo.utils.ResourceFileReadUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.annotation.Resource;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

/**
 * @author youngbear
 * @email youngbear@aliyun.com
 * @date 2022-09-22 23:17
 * @blog <a href="https://blog.csdn.net/next_second">...</a>
 * @github <a href="https://github.com/YoungBear">...</a>
 * @description test for @link EmployeeController
 */
@WebMvcTest(EmployeeController.class)
@SpringJUnitConfig(classes = EmployeeController.class)
@DisplayName("EmployeeController Test")
public class EmployeeControllerTest {
    @Resource
    private MockMvc mockMvc;

    @MockBean
    private IEmployeeService employeeService;

    @Test
    @DisplayName("add 成功")
    public void add_test_success() throws Exception {
        when(employeeService.addEmployee(any())).thenReturn(1);
        mockMvc.perform(MockMvcRequestBuilders.post("/employee/add")
                        .header("X-USER-ACCOUNT", "John")
                        .content(ResourceFileReadUtils.readString("json/employee/EmployeeAdd.json"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.code").value(Result.SUCCESS_CODE))
                .andExpect(jsonPath("$.msg").value(Result.SUCCESS_MESSAGE))
                .andExpect(jsonPath("$.result.total").value(1))
                .andExpect(jsonPath("$.result.data[0]").value(1));
        verify(employeeService).addEmployee(any());
    }

    @Test
    @DisplayName("add 异常")
    public void add_test_exception() throws Exception {
        when(employeeService.addEmployee(any(EmployeeVo.class))).thenThrow(new DemoException(ErrorEnum.UNKNOWN_ERROR));
        ErrorEnum errorEnum = ErrorEnum.UNKNOWN_ERROR;
        mockMvc.perform(MockMvcRequestBuilders.post("/employee/add")
                        .header("X-USER-ACCOUNT", "John")
                        .content(ResourceFileReadUtils.readString("json/employee/EmployeeAdd.json"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.code").value(errorEnum.getErrorCode()))
                .andExpect(jsonPath("$.msg").value(errorEnum.getErrorMessage()));
        verify(employeeService).addEmployee(any());
    }
}
