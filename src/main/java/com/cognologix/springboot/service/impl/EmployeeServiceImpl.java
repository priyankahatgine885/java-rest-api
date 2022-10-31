package com.cognologix.springboot.service.impl;
import com.cognologix.springboot.dao.EmployeeDao;
import com.cognologix.springboot.dto.employee.EmployeeDTO;
import com.cognologix.springboot.dto.employee.EmployeeListResponse;
import com.cognologix.springboot.dto.employee.EmployeeResponse;
import com.cognologix.springboot.entities.Employee;
import com.cognologix.springboot.exception.EmptyListException;
import com.cognologix.springboot.exception.NameAlreadyExistException;
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
        if(employeeList.isEmpty()) {
            throw new EmptyListException("List is empty");
        }
        return new EmployeeListResponse(employeeList, employeeList.size());
    }

    @Override
    public Employee getEmployeeById(int id) {

        return employeeDao.findById(id).get();
    }

    @Override
    public EmployeeResponse addEmployee(@RequestBody EmployeeDTO employee)throws NameAlreadyExistException {
        if(employeeDao.findEmployeeByName(employee.getName()) != null){
            throw new NameAlreadyExistException("Employee Name already exist");
        }else{
            return  new EmployeeResponse(employeeDao.save(new Employee(employee)));
        }
    }

    @Override
    public EmployeeResponse updateEmployee(int id, Employee employee)throws NullPointerException {
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
