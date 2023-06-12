package com.programming.rajeev.Employee.controller;

import com.programming.rajeev.Employee.entity.Employee;
import com.programming.rajeev.Employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/employee")
@CrossOrigin
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;




    @PostMapping
    public String registerEmployee(@RequestBody Employee employee)
    {
        return  employeeService.registerEmployee(employee);

    }

    @GetMapping
    public List<Employee> getAllEmployee()
    {
        return employeeService.getAllEmployee();

    }





}
