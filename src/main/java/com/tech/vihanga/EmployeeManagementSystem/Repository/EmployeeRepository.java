package com.tech.vihanga.EmployeeManagementSystem.Repository;

import com.tech.vihanga.EmployeeManagementSystem.Models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {

    void deleteEmployeeById(Long id);

    Optional<Employee> findEmployeeById(Long id);
}
