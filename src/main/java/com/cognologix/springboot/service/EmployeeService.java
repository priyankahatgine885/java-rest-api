package com.cognologix.springboot.service;

import com.cognologix.springboot.dto.BaseResponse;
import com.cognologix.springboot.dto.employee.EmployeeDTO;
import com.cognologix.springboot.dto.employee.EmployeeListResponse;
import com.cognologix.springboot.dto.employee.EmployeeResponse;
import com.cognologix.springboot.entities.Employee;

public interface EmployeeService {
    BaseResponse getEmployees();
    Employee getEmployeeById(int id);
    BaseResponse addEmployee(EmployeeDTO employee);
    BaseResponse updateEmployee(int id, Employee employee);
    void deleteEmployee(int id);
}
