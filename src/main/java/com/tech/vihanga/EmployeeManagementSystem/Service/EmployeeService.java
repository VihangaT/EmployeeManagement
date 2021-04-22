package com.tech.vihanga.EmployeeManagementSystem.Service;

import com.tech.vihanga.EmployeeManagementSystem.Exceptions.UserNotFoundException;
import com.tech.vihanga.EmployeeManagementSystem.Models.Employee;
import com.tech.vihanga.EmployeeManagementSystem.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepo;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepo){
        this.employeeRepo = employeeRepo;
    }

    public Employee addEmployee(Employee employee){
        employee.setEmployeeCode(UUID.randomUUID().toString());
        return employeeRepo.save(employee);
    }

    public List<Employee> findAllEmployees(){
        return employeeRepo.findAll();

    }

    public Employee updateEmployee(Employee emploee){
        return employeeRepo.save(emploee);
    }

    public void deleteEmployee(Long id)
    {
        employeeRepo.deleteEmployeeById(id);
    }

    public  Employee findEmployeeById(Long id){
        return employeeRepo.findEmployeeById(id).orElseThrow(()-> new UserNotFoundException("User by ID "+id + " does not exists"));
    }


}
