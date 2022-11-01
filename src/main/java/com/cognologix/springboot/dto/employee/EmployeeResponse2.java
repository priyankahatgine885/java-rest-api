package com.cognologix.springboot.dto.employee;

import com.cognologix.springboot.dto.BaseResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeResponse2 extends BaseResponse {
    private String requestParam;
    private String pathVariable;

    public EmployeeResponse2(String requestParam, String pathVariable) {
        super(true);
        this.requestParam = requestParam;
        this.pathVariable = pathVariable;
    }
}
