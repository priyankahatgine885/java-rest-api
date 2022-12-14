package com.cognologix.springboot.dto.employee;
import com.cognologix.springboot.dto.BaseResponse;
import com.cognologix.springboot.entities.Employee;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class EmployeeListResponse extends BaseResponse {
    private List<Employee> employeeList;
    private Integer size;
    public EmployeeListResponse(List<Employee> e, Integer size) {
        super(true);
        this.employeeList = e;
        this.size = size;
    }
}
