package com.cognologix.springboot.controller;

import com.cognologix.springboot.dto.BaseResponse;
import com.cognologix.springboot.dto.employee.EmployeeDTO;
import com.cognologix.springboot.dto.employee.EmployeeResponse;
import com.cognologix.springboot.entities.Employee;
import com.cognologix.springboot.exception.RecordNotFoundException;
import com.cognologix.springboot.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employee")
    public ResponseEntity<BaseResponse> getEmployees() {
        BaseResponse response = null;
        HttpStatus statusCode = null;
        try {
            response = this.employeeService.getEmployees();
            response.setMessage("'GET method' received request ");
            statusCode = response.getSuccess() ? HttpStatus.OK : HttpStatus.NO_CONTENT;
        } catch (Exception ex) {
            ex.getCause();
        }
        return new ResponseEntity<>(response, statusCode);
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable int id) throws RecordNotFoundException {
        Employee employee = null;
        try {
            employee = employeeService.getEmployeeById(id);
        } catch (RuntimeException ex) {
            System.out.println();
        }
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @PostMapping("/employee")
    public ResponseEntity<BaseResponse> addEmployee(@RequestBody EmployeeDTO employee) {
        BaseResponse response = null;
        HttpStatus statusCode = null;
        try {
            response = this.employeeService.addEmployee(employee);
            response.setMessage("'POST method' received request ");
            statusCode = response.getSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        } catch (Exception ex) {
            ex.getCause();
        }
        return new ResponseEntity<>(response, statusCode);
    }

    @PutMapping("/employee/{id}")
    public ResponseEntity<BaseResponse> updateEmployee(@PathVariable int id, @RequestBody Employee employee) {
        BaseResponse response = null;
        HttpStatus statusCode = null;
        try {
            response = this.employeeService.updateEmployee(id, employee);
            response.setMessage("'PUT method' received request ");
            statusCode = response.getSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        } catch (Exception ex) {
            ex.getCause();
        }
        return new ResponseEntity<>(response, statusCode);
    }

    @DeleteMapping("/employee/{id}")
    public ResponseEntity<EmployeeResponse> deleteEmployee(@PathVariable int id) {
        EmployeeResponse employeeResponse = null;
        HttpStatus statusCode = null;
        try {
            this.employeeService.deleteEmployee(id);
            employeeResponse = new EmployeeResponse(true);
            employeeResponse.setMessage("'DELETE method' received request ");
            statusCode = employeeResponse.getSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        } catch (Exception ex) {
            ex.getCause();
        }
        return new ResponseEntity<>(employeeResponse, statusCode);
    }
}
