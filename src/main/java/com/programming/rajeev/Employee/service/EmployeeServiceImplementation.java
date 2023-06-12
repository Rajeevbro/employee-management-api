package com.programming.rajeev.Employee.service;

import com.programming.rajeev.Employee.entity.Employee;
import com.programming.rajeev.Employee.repository.EmployeeManagementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImplementation implements EmployeeService{
    @Autowired
    private EmployeeManagementRepository employeeManagementRepository;
    @Override
    public String registerEmployee(Employee employee) {
        employeeManagementRepository.save(employee);

        return "sucess";
    }

    @Override
    public List<Employee> getAllEmployee() {
        return employeeManagementRepository.findAll();
    }
}
