package com.programming.rajeev.Employee.repository;

import com.programming.rajeev.Employee.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeManagementRepository extends JpaRepository<Employee,Long> {
}
