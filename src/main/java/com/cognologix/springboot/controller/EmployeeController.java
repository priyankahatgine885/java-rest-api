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
        BaseResponse response = this.employeeService.getEmployees();
        response.setMessage("'GET method' received request ");
        HttpStatus statusCode = response.getSuccess() ? HttpStatus.OK : HttpStatus.NO_CONTENT;
        return new ResponseEntity<>(response, statusCode);
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable int id) throws RecordNotFoundException {
        if ((id <= 0)) {
            throw new RecordNotFoundException("Invalid employee id : " + id);
        }
        Employee employee = employeeService.getEmployeeById(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @PostMapping("/employee")
    public ResponseEntity<BaseResponse> addEmployee(@RequestBody EmployeeDTO employee) {
        BaseResponse response = this.employeeService.addEmployee(employee);
        response.setMessage("'POST method' received request ");
        final HttpStatus statusCode = response.getSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(response, statusCode);
    }

    @PutMapping("/employee/{id}")
    public ResponseEntity<BaseResponse> updateEmployee(@PathVariable int id, @RequestBody Employee employee) {
        try {
           BaseResponse response = this.employeeService.updateEmployee(id, employee);
            response.setMessage("'PUT method' received request ");
            final HttpStatus statusCode = response.getSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(response, statusCode);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/employee/{id}")
    public ResponseEntity<EmployeeResponse> deleteEmployee(@PathVariable int id) {
        this.employeeService.deleteEmployee(id);
        EmployeeResponse employeeResponse = new EmployeeResponse(true);
        employeeResponse.setMessage("'DELETE method' received request ");
        final HttpStatus statusCode = employeeResponse.getSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(employeeResponse, statusCode);
    }
}
