package com.programming.rajeev.Employee.service;

import com.programming.rajeev.Employee.dto.LoginDto;
import com.programming.rajeev.Employee.dto.UserRegisterLoginResponse;
import com.programming.rajeev.Employee.entity.User;

import java.util.List;

public interface UserService {
    public UserRegisterLoginResponse registerUser(User user);

    public UserRegisterLoginResponse loginUser(LoginDto loginDto);

    public List<User> getAllUser();

    public String removeUser(Long id);
}
