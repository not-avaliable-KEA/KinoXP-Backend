package com.example.kinoxpbackend.employee.controller;


import com.example.kinoxpbackend.employee.DTOs.EmployeeDTO;
import com.example.kinoxpbackend.employee.models.Employee;
import com.example.kinoxpbackend.employee.services.EmployeeService;
import org.hibernate.result.UpdateCountOutput;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/*
@Author Sofia Abrahamsen + Lasse Bøgeskov-Jensen
 */
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    EmployeeService service = new EmployeeService();
    Employee employee = new Employee();
    //create employee @get og @post
    @GetMapping("/create")
    public String createEmployee(){
        return "createEmployee";
    }
    //delete employee @get og @post
    @GetMapping("/delete")
    public String deleteEmployee(){
        return "deleteEmployee";
    }
    //update employee @get og @post
    @GetMapping("/update")
    public String updateEmployee(){
        return "updateEmployee";
    }
    //view employee @get og @post
    @GetMapping("/view")
    public String viewEmployees(){
        return "ViewEmployees";
    }

}
