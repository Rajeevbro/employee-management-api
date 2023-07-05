package com.programming.rajeev.Employee.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor@NoArgsConstructor
@Builder
public class UserRegisterLoginResponse {
    private String userName;

    private  String token;
}
