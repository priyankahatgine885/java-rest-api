package com.cognologix.springboot.dto.employee;
import com.cognologix.springboot.dto.BaseResponse;
import com.cognologix.springboot.entities.Employee;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
public class EmployeeResponse extends BaseResponse {
    private Employee employee;
    public EmployeeResponse(Employee e) {
        super(true);
        this.employee = e;
    }
    public EmployeeResponse(boolean isSuccess) {
        super(isSuccess);
    }
}
