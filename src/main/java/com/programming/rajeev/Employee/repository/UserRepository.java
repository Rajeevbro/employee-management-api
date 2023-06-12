package com.programming.rajeev.Employee.repository;

import com.programming.rajeev.Employee.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    public Optional<User>findByUserName(String name);
}
