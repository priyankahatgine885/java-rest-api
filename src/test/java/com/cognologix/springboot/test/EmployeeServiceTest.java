package com.cognologix.springboot.test;
import com.cognologix.springboot.dao.EmployeeDao;
import com.cognologix.springboot.dto.BaseResponse;
import com.cognologix.springboot.dto.employee.EmployeeDTO;
import com.cognologix.springboot.dto.employee.EmployeeListResponse;
import com.cognologix.springboot.entities.Employee;
import com.cognologix.springboot.exception.EmptyListException;
import com.cognologix.springboot.exception.NameAlreadyExistException;
import com.cognologix.springboot.exception.ResourceNotFoundException;
import com.cognologix.springboot.service.EmployeeService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeServiceTest {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    EmployeeDao employeeDao;

    @Test
    public void addEmployee_addNewEmployee_success() {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setName("Janavi Mali");
        employeeDTO.setSalary(23000.0f);
        BaseResponse employee = employeeService.addEmployee(employeeDTO);
        Assert.assertNotNull(employee);

    }

    @Test
    public void addEmployee_addDuplicateEmployee_nameAlreadyExistException() {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setName("Amit Patil11111");
        employeeDTO.setSalary(2222);
        BaseResponse response = employeeService.addEmployee(employeeDTO);
        Exception exception = Assert.assertThrows(NameAlreadyExistException.class, () -> {
            employeeService.addEmployee(employeeDTO);
        });
        String expectedMessage = "Employee Name already exist";
        String actualMessage = exception.getMessage();
        Assert.assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void getListEmployees_getListEmployees_emptyListException(){
        EmployeeListResponse response = employeeService.getEmployees();
        if(response.getSize() != 0) {
            Assert.assertNotNull(response);
        }else {
            throw new EmptyListException("List is empty");
        }
    }

    @Test
    public void updateEmployee_updateNewEmployee_success() {
        Employee employee = new Employee();
        employee.setName("Dipali Patil");
        employee.setSalary(20000);
        BaseResponse response = employeeService.updateEmployee(1, employee);
        Assert.assertNotNull(response);
    }

    @Test
    public void deleteEmployee_success() {
        Employee employee = employeeDao.findById(1).get();
        if(employeeDao.existsById(employee.getId())){
            employeeService.deleteEmployee(employee.getId());
        }else {
            throw new ResourceNotFoundException("Employee not found for this id : " + 1);
        }
    }
}
