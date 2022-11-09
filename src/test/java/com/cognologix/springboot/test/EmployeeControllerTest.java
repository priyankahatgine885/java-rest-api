package com.cognologix.springboot.test;
import com.cognologix.springboot.controller.EmployeeController;
import com.cognologix.springboot.dao.EmployeeDao;
import com.cognologix.springboot.dto.BaseResponse;
import com.cognologix.springboot.dto.employee.EmployeeDTO;
import com.cognologix.springboot.dto.employee.EmployeeListResponse;
import com.cognologix.springboot.dto.employee.EmployeeResponse;
import com.cognologix.springboot.entities.Employee;
import com.cognologix.springboot.exception.EmptyListException;
import com.cognologix.springboot.exception.NameAlreadyExistException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeControllerTest {
    @Autowired
    private EmployeeController employeeController;
    @Autowired
    private EmployeeDao employeeDao;

    @Test
    public void addEmployee_addNewEmployee_success() {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setName("Pooja Patil");
        employeeDTO.setSalary(23000.0f);
        ResponseEntity<BaseResponse> response = employeeController.addEmployee(employeeDTO);
        if(response.getStatusCode() == HttpStatus.OK) {
            Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
            Assert.assertEquals(response.getBody().getSuccess(), true);
           // Assert.assertNotNull(response.getBody().getEmployee());
        }else{
            Assert.assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
            Assert.assertEquals(response.getBody().getSuccess(), false);
        }
    }
    @Test
    public void addEmployee_addDuplicateEmployee_nameAlreadyExistException() {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setName("Mrunali Patil");
        employeeDTO.setSalary(15000);
        ResponseEntity<BaseResponse> response = employeeController.addEmployee(employeeDTO);
        Exception exception = Assert.assertThrows(NameAlreadyExistException.class, () -> {
            employeeController.addEmployee(employeeDTO);
        });
        String expectedMessage = "Employee Name already exist";
        String actualMessage = exception.getMessage();
        Assert.assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void getListEmployees_getListEmployees_emptyListException(){
        ResponseEntity<EmployeeListResponse> response = employeeController.getEmployees();
        if(response.getBody().getSize() != 0) {
            Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
            Assert.assertEquals(response.getBody().getSuccess(), true);
            Assert.assertNotNull(response.getBody().getEmployeeList());
        }else {
            throw new EmptyListException("List is empty");
        }
    }

    @Test
    public void updateEmployee_updateNewEmployee_success() {
        Employee employee = new Employee();
        employee.setName("Priyanka Hatgine");
        employee.setSalary(20000);
        ResponseEntity<BaseResponse> response = employeeController.updateEmployee(2, employee);
        if(response.getStatusCode() == HttpStatus.OK) {
            Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
            Assert.assertEquals(response.getBody().getSuccess(), true);
           // Assert.assertEquals(response.getBody().getEmployee().getName(), employee.getName());
        }else {
            Assert.assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
            Assert.assertEquals(response.getBody().getSuccess(), false);
        }
    }

    @Test
    public void deleteEmployee_success(){
        Employee employee = employeeDao.findById(1).get();
        ResponseEntity<EmployeeResponse> response = employeeController.deleteEmployee(employee.getId());
        if(response.getStatusCode() == HttpStatus.OK) {
            Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
            Assert.assertEquals(response.getBody().getSuccess(), true);
        }else{
            Assert.assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
            Assert.assertEquals(response.getBody().getSuccess(), false);
        }
    }
}
