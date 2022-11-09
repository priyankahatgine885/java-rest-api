package com.cognologix.springboot.service;

import com.cognologix.springboot.dto.BaseResponse;
import com.cognologix.springboot.dto.employee.EmployeeDTO;
import com.cognologix.springboot.dto.employee.EmployeeListResponse;
import com.cognologix.springboot.dto.employee.EmployeeResponse;
import com.cognologix.springboot.entities.Employee;
import org.springframework.stereotype.Service;


public interface EmployeeService {
    EmployeeListResponse getEmployees();
    Employee getEmployeeById(int id);
    BaseResponse addEmployee(EmployeeDTO employee);
    BaseResponse updateEmployee(int id, Employee employee);
    void deleteEmployee(int id);
}
