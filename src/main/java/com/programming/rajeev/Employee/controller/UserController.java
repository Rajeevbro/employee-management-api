package com.programming.rajeev.Employee.controller;

import com.programming.rajeev.Employee.dto.LoginDto;
import com.programming.rajeev.Employee.dto.UserRegisterLoginResponse;
import com.programming.rajeev.Employee.entity.User;
import com.programming.rajeev.Employee.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/user/register")
    public UserRegisterLoginResponse registerUser(@RequestBody User user)
    {
        return userService.registerUser(user);
    }

    @PostMapping("/user/login")
            public UserRegisterLoginResponse loginUser(@RequestBody LoginDto loginDto)
    {
        return userService.loginUser(loginDto);
    }


    @GetMapping("/user")
    public List<User> getAllUser()
    {
        return userService.getAllUser();

    }

    @DeleteMapping("/user/remove/{id}")
    public String removeUser(@PathVariable("id") Long id)
    {
        return userService.removeUser(id);

    }
}
