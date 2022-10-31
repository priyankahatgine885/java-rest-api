package com.cognologix.springboot.controller;
import com.cognologix.springboot.dto.employee.EmployeeDTO;
import com.cognologix.springboot.dto.employee.EmployeeListResponse;
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
    public ResponseEntity<EmployeeListResponse> getEmployees() {
        EmployeeListResponse empList = this.employeeService.getEmployees();
        HttpStatus statusCode = empList.getSuccess() ? HttpStatus.OK : HttpStatus.NO_CONTENT;
        return new ResponseEntity<>(empList, statusCode);
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
    public ResponseEntity<EmployeeResponse> addEmployee(@RequestBody EmployeeDTO employee) {
        EmployeeResponse emp = this.employeeService.addEmployee(employee);
        emp.setMessage("Employee created successfully");
        final HttpStatus statusCode = emp.getSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(emp, statusCode);
    }

    @PutMapping("/employee/{id}")
    public ResponseEntity<EmployeeResponse> updateEmployee(@PathVariable int id, @RequestBody Employee employee) {
        try {
            EmployeeResponse employeeResponse = this.employeeService.updateEmployee(id, employee);
            employeeResponse.setMessage("Employee updated successfully");
            final HttpStatus statusCode = employeeResponse.getSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(employeeResponse, statusCode);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/employee/{id}")
    public ResponseEntity<EmployeeResponse> deleteEmployee(@PathVariable int id) {
            this.employeeService.deleteEmployee(id);
            EmployeeResponse employeeResponse = new EmployeeResponse(true);
            employeeResponse.setMessage("Employee deleted successfully");
            final HttpStatus statusCode = employeeResponse.getSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(employeeResponse, statusCode);
    }
}
