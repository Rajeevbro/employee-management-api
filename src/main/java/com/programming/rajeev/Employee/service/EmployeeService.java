package com.programming.rajeev.Employee.service;

import com.programming.rajeev.Employee.entity.Employee;

import java.util.List;

public interface EmployeeService {
    public String registerEmployee(Employee employee);

    public List<Employee> getAllEmployee();
}
