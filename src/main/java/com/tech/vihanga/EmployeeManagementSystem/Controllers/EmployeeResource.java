package com.tech.vihanga.EmployeeManagementSystem.Controllers;

import com.tech.vihanga.EmployeeManagementSystem.Models.Employee;
import com.tech.vihanga.EmployeeManagementSystem.Service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/vi/employee")
public class EmployeeResource {
    private final EmployeeService employeeService;

    private EmployeeResource(EmployeeService employeeService){
        this.employeeService=employeeService;
    }


    @GetMapping("/all")
    public ResponseEntity<List<Employee>> getAllEmployees(){
        List<Employee> employees= employeeService.findAllEmployees();
        return  new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id){
        Employee employee=employeeService.findEmployeeById(id);
        return new ResponseEntity<>(employee,HttpStatus.OK);
    }

    @PostMapping("/add")
    public  ResponseEntity<Employee> addEmployee(@RequestBody Employee employee){
        Employee employeeNew= employeeService.addEmployee(employee);
        return new ResponseEntity<>(employeeNew,HttpStatus.CREATED);
    }
}
