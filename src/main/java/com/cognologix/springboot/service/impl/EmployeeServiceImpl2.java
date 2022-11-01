package com.cognologix.springboot.service.impl;

import com.cognologix.springboot.dao.EmployeeDao;
import com.cognologix.springboot.dto.BaseResponse;
import com.cognologix.springboot.dto.employee.EmployeeResponse2;
import com.cognologix.springboot.dto.employee.EmployeeDTO;
import com.cognologix.springboot.dto.employee.EmployeeResponse;
import com.cognologix.springboot.dto.employee.EmployeeResponse3;
import com.cognologix.springboot.entities.Employee;
import com.cognologix.springboot.exception.NameAlreadyExistException;
import com.cognologix.springboot.service.EmployeeService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;

@Service
public class EmployeeServiceImpl2 implements EmployeeService2 {
    @Autowired
    private EmployeeDao employeeDao;


    @Override
    public EmployeeResponse getWithRequestEmployee(EmployeeDTO employeeDTO) {
        return null;
    }

    @Override
    public EmployeeResponse2 getEmployeeById(String pathParam, String queryParam) {
        return new EmployeeResponse2(pathParam,queryParam);
    }

    @Override
    public EmployeeResponse3 addEmployee(@RequestBody EmployeeDTO employee, String pathParam, String queryParam){
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(employee.getId());
        employeeDTO.setName(employee.getName());
        employeeDTO.setSalary(employee.getSalary());
        return new EmployeeResponse3(employeeDTO, pathParam, queryParam);
    }
}


