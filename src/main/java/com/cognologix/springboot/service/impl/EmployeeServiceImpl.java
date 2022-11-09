package com.cognologix.springboot.service.impl;
import com.cognologix.springboot.dao.EmployeeDao;
import com.cognologix.springboot.dto.BaseResponse;
import com.cognologix.springboot.dto.employee.EmployeeDTO;
import com.cognologix.springboot.dto.employee.EmployeeListResponse;
import com.cognologix.springboot.dto.employee.EmployeeResponse;
import com.cognologix.springboot.entities.Employee;
import com.cognologix.springboot.exception.EmptyListException;
import com.cognologix.springboot.exception.NameAlreadyExistException;
import com.cognologix.springboot.exception.RecordNotFoundException;
import com.cognologix.springboot.exception.ResourceNotFoundException;
import com.cognologix.springboot.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeDao employeeDao;

    @Override
    public EmployeeListResponse getEmployees() {
        List<Employee> employeeList = employeeDao.findAll();
        if(employeeList.size() == 0) {
            throw new EmptyListException("List is empty");
        }
        return new EmployeeListResponse(employeeList);
    }


    @Override
    public Employee getEmployeeById(int id) {
        if ((id <= 0)) {
            throw new RecordNotFoundException("Invalid employee id : " + id);
        }
        return employeeDao.findById(id).get();
    }

    @Override
    public BaseResponse addEmployee(@RequestBody EmployeeDTO employee){
        if(employeeDao.findEmployeeByName(employee.getName()) != null){
            throw new NameAlreadyExistException("Employee Name already exist");
        }else{
            return  new EmployeeResponse(employeeDao.save(new Employee(employee)));
        }
    }

    @Override
    public BaseResponse updateEmployee(int id, Employee employee) {
        Employee employees = employeeDao.findById(id).get();
        if(employees == null){
            throw  new NullPointerException("Null Pointer Exception");
        }
        employees.setName(employee.getName());
        employees.setSalary(employee.getSalary());
        return new EmployeeResponse(employeeDao.save(employees));
    }

    @Override
    public void deleteEmployee(int id) throws ResourceNotFoundException {
        Employee entity = employeeDao.getOne(id);
        if(employeeDao.existsById(id)){
            employeeDao.delete(entity);
        }else {
            throw new ResourceNotFoundException("Employee not found for this id : " + id);
        }
    }
}
