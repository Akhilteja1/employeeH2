package com.example.employee.service;



import com.example.employee.model.Employee;
import java.util.*;

import com.example.employee.repository.EmployeeRepository;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.example.employee.model.EmployeeRowMapper;


@Service
public class EmployeeH2Service implements EmployeeRepository{


    @Autowired
    private JdbcTemplate db;
    @Override
    public ArrayList<Employee> getAllEmployees(){
        List<Employee> songList = db.query("select * from EMPLOYEELIST",new EmployeeRowMapper() );
        ArrayList<Employee> songs = new ArrayList<>(songList);
        return songs;

    }

    @Override
    public Employee getEmployeeById(int employeeId){
        try{
            Employee song = db.queryForObject("select * from EMPLOYEELIST where employeeid = ? ",new EmployeeRowMapper(),employeeId);

        return song;

        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        
    }

    @Override
    public Employee addEmployee(Employee employee){
        db.update("insert into EMPLOYEELIST(employeeName,email,department) values(?,?,?)",employee.getEmployeeName(),employee.getEmail(),employee.getDepartment());
        Employee savedSong =db.queryForObject("select * from EMPLOYEELIST where employeename = ? and email = ? and department = ?  ",new EmployeeRowMapper(),employee.getEmployeeName(),employee.getEmail(),employee.getDepartment());
        return savedSong;
    }


     @Override
    public Employee updateEmployee(int employeeId, Employee employee){

        try{
            Employee checkSong = db.queryForObject("select * from EMPLOYEELIST where employeeid = ? ",new EmployeeRowMapper(),employeeId);
            if(checkSong != null){
            
            db.update("update EMPLOYEELIST set employeename = ? and email = ? where employeeid = ? ",employee.getEmployeeName(),employee.getEmail(),employeeId);
             
            }
            return getEmployeeById(employeeId);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }






         
            
            
        }


        
     @Override
    public void deleteEmployee(int employeeId){
            Employee employee = db.queryForObject("select * from EMPLOYEELIST where employeeId =?",new EmployeeRowMapper(),employeeId);
            if(employee != null){
                db.update("delete from EMPLOYEELIST where employeeid = ?",employeeId);
            throw new ResponseStatusException(HttpStatus.OK);

            }else{
                 
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

            }

              






            

        }      

     

              






            

        










}