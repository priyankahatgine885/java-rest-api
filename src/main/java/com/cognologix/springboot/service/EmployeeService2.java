package com.cognologix.springboot.service;

import com.cognologix.springboot.dto.BaseResponse;
import com.cognologix.springboot.dto.employee.EmployeeResponse2;
import com.cognologix.springboot.dto.employee.EmployeeDTO;
import com.cognologix.springboot.dto.employee.EmployeeResponse;
import com.cognologix.springboot.dto.employee.EmployeeResponse3;


public interface EmployeeService2 {
    EmployeeResponse getWithRequestEmployee(EmployeeDTO employeeDTO);
    EmployeeResponse2 getEmployeeById(String pathParam, String queryParam);
    EmployeeResponse3 addEmployee(EmployeeDTO employee, String queryParam, String pathParam);
}
