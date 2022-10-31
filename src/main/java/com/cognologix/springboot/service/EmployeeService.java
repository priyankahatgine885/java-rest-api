package com.cognologix.springboot.service;

import com.cognologix.springboot.dto.employee.EmployeeDTO;
import com.cognologix.springboot.dto.employee.EmployeeListResponse;
import com.cognologix.springboot.dto.employee.EmployeeResponse;
import com.cognologix.springboot.entities.Employee;

public interface EmployeeService {
    EmployeeListResponse getEmployees();
    Employee getEmployeeById(int id);
    EmployeeResponse addEmployee(EmployeeDTO employee);
    EmployeeResponse updateEmployee(int id, Employee employee);
    void deleteEmployee(int id);
}
