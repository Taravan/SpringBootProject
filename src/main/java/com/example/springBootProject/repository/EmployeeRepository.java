package com.example.springBootProject.repository;

import com.example.springBootProject.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface with basic CRUD operations
 * for working with employee model and its table in database.
 */

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findEmployeeByTelNum(String telNum);
}
