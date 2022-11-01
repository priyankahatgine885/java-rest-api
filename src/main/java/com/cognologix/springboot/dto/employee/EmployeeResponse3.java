package com.cognologix.springboot.dto.employee;

import com.cognologix.springboot.dto.BaseResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeResponse3 extends BaseResponse {
    private EmployeeDTO requestBody;
    private String pathParam;
    private String queryParam;

    public EmployeeResponse3(EmployeeDTO requestBody, String pathParam, String queryParam) {
        super(true);
        this.requestBody = requestBody;
        this.pathParam = pathParam;
        this.queryParam = queryParam;
    }
}
