package com.example.employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
 
import java.util.*;

import com.example.employee.service.EmployeeH2Service;
 
import com.example.employee.model.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
public class EmployeeController{


    @Autowired
    public EmployeeH2Service employeeService;


    @GetMapping("/employees/{employeeId}")
    public Employee getEmployeeById(@PathVariable("employeeId") int employeeId){
        return employeeService.getEmployeeById(employeeId);
    }


    @PutMapping("/employees/{employeeId}")
    public Employee updateEmployee(@PathVariable("employeeId") int employeeId,@RequestBody Employee employee){
        return employeeService.updateEmployee( employeeId, employee);


    }




     @DeleteMapping("/employees/{employeeId}")
    public void deleteEmployee(@PathVariable("employeeId") int employeeId){
        employeeService.deleteEmployee(employeeId);
    }

   


    @GetMapping("/employees")
    public ArrayList<Employee> getAllEmployees(){
        return employeeService.getAllEmployees();
    }

     @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee employee){
        return employeeService.addEmployee(employee);
    }

}