package com.programming.rajeev.Employee.service;

import com.programming.rajeev.Employee.dto.LoginDto;
import com.programming.rajeev.Employee.dto.UserRegisterLoginResponse;
import com.programming.rajeev.Employee.entity.User;

public interface UserService {
    public UserRegisterLoginResponse registerUser(User user);

    public UserRegisterLoginResponse loginUser(LoginDto loginDto);
}
