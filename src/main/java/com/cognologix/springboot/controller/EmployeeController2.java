package com.cognologix.springboot.controller;

import com.cognologix.springboot.dto.BaseResponse;
import com.cognologix.springboot.dto.employee.EmployeeResponse2;
import com.cognologix.springboot.dto.employee.EmployeeDTO;
import com.cognologix.springboot.dto.employee.EmployeeResponse3;
import com.cognologix.springboot.service.EmployeeService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api")
public class EmployeeController2 {
    @Autowired
    private EmployeeService2 employeeService;

    @GetMapping("/employee/{pathParam}")
    public EmployeeResponse2 getEmployeeById(@PathVariable(value = "pathParam") String pathParam,
                                             @RequestParam(value = "queryParam") String queryParam){
        return employeeService.getEmployeeById(pathParam, queryParam);
    }

    @PostMapping("/employee/{pathParam}")
    public EmployeeResponse3 addEmployee(@RequestBody EmployeeDTO employee,
                                         @PathVariable(value = "pathParam") String pathParam,
                                         @RequestParam(value = "queryParam") String queryParam) {
        return employeeService.addEmployee(employee, pathParam, queryParam);
    }
}
